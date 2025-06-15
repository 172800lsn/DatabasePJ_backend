package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.repository.VehicleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // 根据用户 ID 查询车辆信息
    public List<Vehicle> findVehiclesByOwnerId(Long ownerId) {
        return vehicleRepository.findByOwnerId(ownerId);
    }

    // 分页获取车辆列表
    public Page<Vehicle> getAllVehicles(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    // 添加车辆
    public Vehicle addVehicle(Vehicle vehicle) {
        if (vehicle.getOwner() == null || vehicle.getOwner().getId() == null) {
            throw new IllegalArgumentException("车辆所属用户 (owner_id) 不能为空！");
        }
        if (vehicleRepository.findByLicensePlateAndModel(vehicle.getLicensePlate(), vehicle.getModel()).isPresent()) {
            throw new IllegalArgumentException("车辆信息已存在！");
        }
        return vehicleRepository.save(vehicle);
    }

    // 删除车辆
    public void deleteVehicle(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()) {
            throw new IllegalArgumentException("车辆不存在！");
        }
        vehicleRepository.deleteById(vehicleId);
    }

    /**
     * 统计各车型的维修次数和平均维修费用
     */
    public List<Map<String, Object>> getVehicleRepairStatistics() {
        String sql = "SELECT v.model AS model, " +
                "       COUNT(ro.id) AS repairCount, " +
                "       AVG(p.amount) AS averageRepairCost " +
                "FROM vehicles v " +
                "LEFT JOIN repair_orders ro ON v.id = ro.vehicle_id " +
                "LEFT JOIN payments p ON ro.id = p.repair_order_id " +
                "GROUP BY v.model";

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> results = query.getResultList();

        return results.stream().map(row -> Map.of(
                "model", row[0],
                "repairCount", ((Number) row[1]).intValue(),
                "averageRepairCost", row[2] != null ? ((BigDecimal) row[2]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 统计所有车型的维修频率
    public List<Map<String, Object>> getVehicleRepairFrequency() {
        String sql = "SELECT v.model AS model, COUNT(ro.id) AS repairCount " +
                "FROM vehicles v " +
                "LEFT JOIN repair_orders ro ON v.id = ro.vehicle_id " +
                "GROUP BY v.model " +
                "ORDER BY repairCount DESC";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();

        return results.stream().map(row -> Map.of(
                "model", row[0],
                "repairCount", ((Number) row[1]).intValue()
        )).collect(Collectors.toList());
    }

    // 统计特定车型最常出现的故障类型
    public Map<String, Object> getMostFrequentIssueByModel(String model) {
        String sql = "SELECT ro.description AS issueDescription, COUNT(ro.id) AS issueCount " +
                "FROM repair_orders ro " +
                "INNER JOIN vehicles v ON ro.vehicle_id = v.id " +
                "WHERE v.model = :model " +
                "GROUP BY ro.description " +
                "ORDER BY issueCount DESC " +
                "LIMIT 1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("model", model);

        List<Object[]> results = query.getResultList();
        if (!results.isEmpty()) {
            Object[] row = results.get(0);
            return Map.of(
                    "issueDescription", row[0],
                    "issueCount", ((Number) row[1]).intValue()
            );
        }
        return Map.of(); // 如果没有数据返回空对象
    }

}