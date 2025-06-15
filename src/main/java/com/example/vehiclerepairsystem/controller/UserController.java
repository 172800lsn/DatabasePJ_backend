package com.example.vehiclerepairsystem.controller;

import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.service.UserService;
import com.example.vehiclerepairsystem.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*") // 可根据需要限制来源
@RestController
@RequestMapping("api/test/user")
public class UserController {

    private final UserService userService;
    private final VehicleService vehicleService;

    public UserController(UserService userService, VehicleService vehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    // 获取用户基本信息
    @PostMapping("/details")
    public ResponseEntity<?> getUserDetails(@RequestBody Map<String,String> UserData) {
        System.out.println("Received data: " + UserData);
        String username = UserData.get("username");
        System.out.println("Username: " + username);
        try {
            User user = userService.findByUsernameReally(username);
//            System.out.println("User id: " + user.getId());
//            System.out.println("workType: " + user.getWorkType());
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("role", user.getRole());
            response.put("username", user.getUsername());
            response.put("name", user.getName());
            response.put("email", user.getEmail());
            response.put("workType", user.getWorkType());
            response.put("hourlyRate", user.getHourlyRate());

            System.out.println("User found: " + response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("其他错误: " + e.getMessage());
        }
    }

    // 获取用户绑定的车辆信息
    @PostMapping("/vehicles")
    public ResponseEntity<?> getUserVehicles(@RequestBody Map<String,String> UserData) {
        // 通过 Spring Security 获取当前登录用户的id
        //error: 传进来的数据是username，应该根据username查询id
        try {
            User user = userService.findByUsernameReally(UserData.get("username"));
            List<Vehicle> vehicles = vehicleService.findVehiclesByOwnerId(user.getId());
            return ResponseEntity.ok(vehicles.stream().map(vehicle ->{
                Map<String, Object> response = new HashMap<>();
                response.put("id", vehicle.getId());
                response.put("owner", vehicle.getOwner());
                response.put("licensePlate", vehicle.getLicensePlate());
                response.put("brand", vehicle.getBrand());
                response.put("color", vehicle.getColor());
                response.put("year", vehicle.getYear());
                response.put("model", vehicle.getModel());
                return response;
            }).toList());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("其他错误: " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("内部服务器错误: " + e.getMessage());
        }
    }

    // 分页获取用户列表
    @PostMapping("/get-all")
    public ResponseEntity<?> getAllUsers(@RequestBody Map<String, Object> pageableData) {
        try {
            int page = (int) pageableData.get("page");
            int size = (int) pageableData.get("size");
            String sortBy = pageableData.getOrDefault("sortBy", "id").toString();
            String direction = pageableData.getOrDefault("direction", "ASC").toString();

            Page<User> usersPage = userService.getAllUsers(PageRequest.of(page, size,
                    direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));

            Map<String, Object> response = new HashMap<>();
            response.put("users", usersPage.getContent());
            response.put("total", usersPage.getTotalElements());
            response.put("pages", usersPage.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误: " + e.getMessage());
        }
    }

    // 删除用户
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("用户删除成功！");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("删除失败: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误: " + e.getMessage());
        }
    }

}