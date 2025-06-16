package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.*;
import com.example.vehiclerepairsystem.repository.*;
import jakarta.transaction.Transactional;
import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.scheduling.annotation.Async;

import com.example.vehiclerepairsystem.DTO.RepairOrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RepairService {
    private final RepairOrderRepository repairOrderRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final AssignmentRepository assignmentRepository;
    private final PaymentRepository PaymentRepository;

    public RepairService(RepairOrderRepository repairOrderRepository, UserRepository userRepository, VehicleRepository vehicleRepository, AssignmentRepository assignmentRepository, PaymentRepository paymentRepository) {
        this.repairOrderRepository = repairOrderRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.assignmentRepository = assignmentRepository;
        this.PaymentRepository = paymentRepository;
    }
    @Async
    @EventListener
    @Transactional
    public void handleRepairOrder(RepairOrderCreatedEvent event) {
        OrderWorkerAssignment assignment = new OrderWorkerAssignment();
        assignment.setRepairOrder(event.repairOrder());
        RepairOrder repairOrder = event.repairOrder();
        List<User> workers = userRepository.findByRole(User.Role.WORKER);
        if (workers.isEmpty()) {
            throw new IllegalStateException("没有可用的维修工人");
        }
        User assignedWorker = workers.get(new Random().nextInt(workers.size()));
        // 创建分配关系
        assignment.setWorker(assignedWorker);
        // 保存分配关系（需要注入OrderWorkerAssignmentRepository）
        event.repairOrder().setWorker(assignment);
        event.repairOrder().setStatus(RepairOrder.Status.TO_ACCEPT);
        assignmentRepository.save(assignment);
    }
    @Transactional
    public void updateMaterials(Long orderId,List<Map<String, Object>> materials) {
        RepairOrder order = repairOrderRepository.findById(orderId)
              .orElseThrow(() -> new IllegalArgumentException("维修记录未找到"));
        User worker = order.getWorker().getWorker();
        order.getMaterials().clear();
        List<Material> materialList = materials.stream().map(mat -> {
            Material material = new Material();
            Object nameObj = mat.get("name");
            material.setName(nameObj != null ? nameObj.toString() : "");
            material.setQuantity(((Integer) mat.get("quantity")).intValue());
            material.setPrice(new BigDecimal(0));
            material.setRepairOrder(order);
            material.setSubmittedByWorker(worker);
            return material;
        }).collect(Collectors.toList());

        order.getMaterials().addAll(materialList);
        repairOrderRepository.save(order);
    }
    public List<RepairOrder> findRepairOrdersByWorkerNameAndStatus(String workerName, RepairOrder.Status status) {
        User worker = userRepository.findByUsername(workerName)
               .orElseThrow(() -> new IllegalArgumentException("用户未找到"));
        return repairOrderRepository.findByWorker_WorkerAndStatus(worker, status);
    }
    public RepairOrder findRepairOrderById(Long id) {
        return repairOrderRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("维修记录未找到"));
    }
    @Transactional
    public void acceptTask(Long taskId, String workerName) {
        RepairOrder repairOrder = repairOrderRepository.findById(taskId)
               .orElseThrow(() -> new IllegalArgumentException("维修记录未找到"));
        repairOrder.setStatus(RepairOrder.Status.IN_PROGRESS);
    }
    @Transactional
    public void rejectTask(Long taskId, String workerName) {
        RepairOrder repairOrder = repairOrderRepository.findById(taskId)
              .orElseThrow(() -> new IllegalArgumentException("维修记录未找到"));
       OrderWorkerAssignment assignment = assignmentRepository.findByRepairOrderId(taskId);
        List<User> workers = userRepository.findByRole(User.Role.WORKER);
        if (workers.isEmpty()) {
            throw new IllegalStateException("没有可用的维修工人");
        }
        User assignedWorker = workers.get(new Random().nextInt(workers.size()));
        assignment.setWorker(assignedWorker);
    }
    @Transactional
    public void completeTask(Long taskId) {
        RepairOrder repairOrder = repairOrderRepository.findById(taskId)
               .orElseThrow(() -> new IllegalArgumentException("维修记录未找到"));
        repairOrder.setStatus(RepairOrder.Status.COMPLETED);
    }

}
