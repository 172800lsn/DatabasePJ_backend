package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.OrderWorkerAssignment;
import com.example.vehiclerepairsystem.model.RepairOrder;
import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.repository.RepairOrderRepository;
import com.example.vehiclerepairsystem.repository.UserRepository;
import com.example.vehiclerepairsystem.repository.VehicleRepository;
import com.example.vehiclerepairsystem.DTO.RepairOrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepairOrderService {

    private final RepairOrderRepository repairOrderRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public RepairOrderService(RepairOrderRepository repairOrderRepository,
                              UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.repairOrderRepository = repairOrderRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public RepairOrder reportRepair(String requestUsername, String licensePlate, String vehicleModel, String description) {
        // 获取用户信息
        User requestUser = userRepository.findByUsername(requestUsername)
                .orElseThrow(() -> new IllegalArgumentException("用户未找到"));
        System.out.println("User found: " + requestUser);

        // 查询车辆是否已存在
        Vehicle vehicle = vehicleRepository.findByLicensePlateAndModel(licensePlate, vehicleModel)
                .orElseThrow(() -> new IllegalArgumentException("车辆信息未找到，请检查车牌号和型号"));
        System.out.println("Vehicle found: " + vehicle);

        // 创建维修订单
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRequestUser(requestUser);
        repairOrder.setVehicle(vehicle);
        repairOrder.setDescription(description);
        repairOrder.setCreateTime(LocalDateTime.now());
        //设置初始状态为PENDING

        repairOrder.setStatus(RepairOrder.Status.PENDING); // 初始状态为
        System.out.println("Repair order created: " + repairOrder);
        repairOrderRepository.save(repairOrder);
        eventPublisher.publishEvent(new RepairOrderCreatedEvent(this, repairOrder));

        return repairOrder; // 保存订单到数据库
    }

    // 根据用户名获取维修记录
    public List<Map<String, Object>> getRepairsByUsername(String username) {
        // 检查用户是否存在
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("用户未找到"));

        // 查询用户的维修记录
        List<RepairOrder> repairOrders;
        if (user.getRole() == User.Role.WORKER) {
            // 如果是维修工，只查询自己分配的维修记录
            repairOrders = repairOrderRepository.findByWorker(user);
        } else {
            // 用户（或管理员）查询自己提交的所有维修记录
            repairOrders = repairOrderRepository.findByRequestUser(user);
        }

        // 装配维修记录的数据结构
        return repairOrders.stream().map(this::formatRepairOrder).collect(Collectors.toList());
    }

    // 格式化单个维修订单的输出
    private Map<String, Object> formatRepairOrder(RepairOrder repairOrder) {
        List<Map<String, Object>> materials = Optional.ofNullable(repairOrder.getMaterials())
                .orElse(List.of())
                .stream()
                .map(material -> {
                    Map<String, Object> materialMap = new HashMap<>();
                    materialMap.put("name", material.getName());
                    materialMap.put("quantity", material.getQuantity());
                    materialMap.put("price", material.getPrice());
                    materialMap.put("subtotal", material.getSubtotal());
                    return materialMap;
                })
                .collect(Collectors.toList());

        double totalCost = materials.stream()
                .mapToDouble(material -> ((Number) material.get("subtotal")).doubleValue())
                .sum();

        Map<String, Object> workerResult = new HashMap<>();
        OrderWorkerAssignment workerAssignment = repairOrder.getWorker();
        User worker = workerAssignment.getWorker();
        workerResult.put("id", worker.getId());
        workerResult.put("name", worker.getName());
        workerResult.put("role", worker.getRole());
        workerResult.put("workType", worker.getWorkType());
        Vehicle vehicle = Optional.ofNullable(repairOrder.getVehicle())
                .orElseThrow(() -> new IllegalArgumentException("RepairOrder 的 Vehicle 不存在"));

        Map<String, Object> result = new HashMap<>();
        result.put("id", repairOrder.getId());
        result.put("licensePlate", vehicle.getLicensePlate());
        result.put("brand", vehicle.getBrand());
        result.put("model", vehicle.getModel());
        result.put("description", repairOrder.getDescription());
        result.put("status", repairOrder.getStatus());
        result.put("startTime", repairOrder.getCreateTime());
        result.put("completionTime", repairOrder.getCompletionTime());
        result.put("worker", workerResult);
        result.put("materials", materials);
        result.put("totalCost", totalCost);

        return result;

    }

    @Transactional
    public void submitFeedback(Long orderId, Boolean isUrgent, String feedback, Double score) {
        // 根据订单ID查询订单
        RepairOrder repairOrder = repairOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("维修订单未找到"));

        // 更新订单反馈信息
        repairOrder.setIsUrgent(isUrgent);
        repairOrder.setFeedback(feedback);
        repairOrder.setScore(score);

        // 保存更新后的订单
        repairOrderRepository.save(repairOrder);
    }

}