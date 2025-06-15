package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.*;
import com.example.vehiclerepairsystem.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;

import com.example.vehiclerepairsystem.DTO.RepairOrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
        event.repairOrder().getWorkers().add(assignment);
        event.repairOrder().setStatus(RepairOrder.Status.IN_PROGRESS);
        assignmentRepository.save(assignment);
    }
    @Transactional
    public void updateRepairOrderStatus(Long orderId,List<Material> materials,boolean isCompleted) {
        RepairOrder repairOrder = repairOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("维修记录未找到"));
        repairOrder.setMaterials(materials);
        BigDecimal totalMaterialCost=materials.stream()
                .map(Material::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(isCompleted){
            repairOrder.setStatus(RepairOrder.Status.COMPLETED);
            repairOrder.setCompletionTime(LocalDateTime.now());
            long hoursWorked = ChronoUnit.HOURS.between(
                    repairOrder.getCreateTime(),
                    repairOrder.getCompletionTime()
            );
            BigDecimal totalAmount = repairOrder.getWorkers().stream()
                    .map(assignment -> assignment.getWorker().getHourlyRate())
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .multiply(BigDecimal.valueOf(hoursWorked));
            Payment payment = new Payment();
            payment.setRepairOrder(repairOrder);
            payment.setTotalMaterialCost(totalMaterialCost);
            payment.setAmount(totalAmount);
        }
    }
    public List<RepairOrder> findRepairOrdersByWorkerName(String workerName) {
        User worker = userRepository.findByUsername(workerName)
               .orElseThrow(() -> new IllegalArgumentException("用户未找到"));
        return repairOrderRepository.findByWorkersWorker(worker);
    }


}
