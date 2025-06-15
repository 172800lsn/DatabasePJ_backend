package com.example.vehiclerepairsystem.controller;

import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "*") // 可根据需要限制来源
public class AdminController {

    private final VehicleService vehicleService;

    public AdminController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // 分页查询车辆列表
    @PostMapping("/vehicles")
    public ResponseEntity<?> getAllVehicles(@RequestBody Map<String, Object> pageableData) {
        try {
            int page = (int) pageableData.getOrDefault("page", 0);
            int size = (int) pageableData.getOrDefault("size", 10);
            String sortBy = pageableData.getOrDefault("sortBy", "id").toString();
            String direction = pageableData.getOrDefault("direction", "ASC").toString();

            Page<Vehicle> vehiclesPage = vehicleService.getAllVehicles(PageRequest.of(page, size,
                    "DESC".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));

            Map<String, Object> response = new HashMap<>();
            response.put("vehicles", vehiclesPage.getContent());
            response.put("totalElements", vehiclesPage.getTotalElements());
            response.put("totalPages", vehiclesPage.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误: " + e.getMessage());
        }
    }

    // 添加车辆
    @PostMapping("/vehicles/add")
    public ResponseEntity<?> addVehicle(@RequestBody Map<String, Object> vehicleData) {
        try {
            Long ownerId = Long.parseLong(vehicleData.get("ownerId").toString());
            String licensePlate = vehicleData.get("licensePlate").toString();
            String brand = vehicleData.get("brand").toString();
            String model = vehicleData.get("model").toString();
            int year = Integer.parseInt(vehicleData.get("year").toString());
            String color = vehicleData.get("color").toString();

            Vehicle vehicle = new Vehicle();
            vehicle.setLicensePlate(licensePlate);
            vehicle.setBrand(brand);
            vehicle.setModel(model);
            vehicle.setYear(year);
            vehicle.setColor(color);
            vehicle.setOwner(new User(ownerId, null, null, null, null, null, null, null)); // 仅设置 ownerId

            Vehicle savedVehicle = vehicleService.addVehicle(vehicle);
            return ResponseEntity.ok(savedVehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("添加车辆失败: " + e.getMessage());
        }
    }

    // 删除车辆
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.ok("车辆删除成功！");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("删除车辆失败: " + e.getMessage());
        }
    }
}