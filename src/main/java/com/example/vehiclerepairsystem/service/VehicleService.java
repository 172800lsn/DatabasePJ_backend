package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}