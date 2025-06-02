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