//维修订单类
package com.example.vehiclerepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "repair_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 申请报修的用户
    @ManyToOne
    @JoinColumn(name = "request_user_id", nullable = false)
    private User requestUser;

    // 关联到车辆
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "repairOrder", cascade = CascadeType.ALL) // 多对多关系的中间表
    private List<OrderWorkerAssignment> workers;

    @OneToOne(mappedBy = "repairOrder", cascade = CascadeType.ALL) // 付款信息
    private Payment payment;


    @Column(nullable = false)
    private String description; // 问题描述

    @Column(nullable = false)
    private LocalDateTime createTime; // 预约时间

    private LocalDateTime completionTime; // 完成时间

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; // 订单状态

    // 与材料的关联关系
    @OneToMany(mappedBy = "repairOrder", cascade =
            CascadeType.ALL, orphanRemoval = true)
    private List<Material> materials; // 关联材料


    public enum Status {
        PENDING, // 待分配
        IN_PROGRESS, // 正在维修
        COMPLETED, // 已完成
        CANCELLED // 已取消
    }
}