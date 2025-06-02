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