package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

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

}