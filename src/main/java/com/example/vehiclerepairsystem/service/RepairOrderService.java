package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.RepairOrder;
import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.repository.RepairOrderRepository;
import com.example.vehiclerepairsystem.repository.UserRepository;
import com.example.vehiclerepairsystem.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RepairOrderService {

    private final RepairOrderRepository repairOrderRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

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

        return repairOrderRepository.save(repairOrder); // 保存订单到数据库
    }
}