//这个文件包含了所有的数据表

//### **材料类 (Material.java)**
package com.example.vehiclerepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 材料名称

    @Column(nullable = false)
    private Integer quantity; // 材料数量

    @Column(nullable = false)
    private BigDecimal price; // 材料单价

    // 关联的维修订单
    @ManyToOne
    @JoinColumn(name = "repair_order_id", nullable = false)
    private RepairOrder repairOrder;

    // 提交维修材料的维修工
    @ManyToOne
    @JoinColumn(name = "submitted_by_worker_id", nullable = false)
    private User submittedByWorker;


    // 获取材料小计价格（数量 * 单价）
    public BigDecimal getSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}

package com.example.vehiclerepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repair_order_worker_assignment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderWorkerAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "repair_order_id", nullable = false)
    private RepairOrder repairOrder;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private User worker;
}

package com.example.vehiclerepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 关联订单
    @OneToOne
    @JoinColumn(name = "repair_order_id", nullable = false)
    private RepairOrder repairOrder;

    @Column(nullable = true)
    private Double totalMaterialCost; // 材料费总和


    @Column(nullable = false)
    private Double amount; // 支付金额

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method; // 支付方式


    public enum PaymentMethod {
        CASH, CREDIT_CARD, DEBIT_CARD, ONLINE
    }
}

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

    //用户反馈信息，可以为空
    @Column(nullable = true)
    private String feedback;

    //用户评分，0.0分到10.0分，可以为空
    @Column(nullable = true)
    private Double score;


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


//用户类
package com.example.vehiclerepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //工种：维修工特有字段
    private String workType;
    //时薪：维修工特有字段
    private Double hourlyRate;

    public enum Role {
        USER,
        WORKER,
        ADMIN
    }
}

//车辆信息类
package com.example.vehiclerepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 车辆所属用户（Many to One）
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String licensePlate; // 车牌号

    @Column(nullable = false,unique = true)
    private String brand; // 品牌

    @Column(nullable = false)
    private String model; // 型号

    @Column(nullable = false)
    private Integer year; // 生产年份

    private String color; // 颜色
}
