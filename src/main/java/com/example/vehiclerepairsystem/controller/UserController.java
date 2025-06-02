package com.example.vehiclerepairsystem.controller;

import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.service.UserService;
import com.example.vehiclerepairsystem.service.VehicleService;
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
//        try {
//            List<Vehicle> vehicles = vehicleService.findVehiclesByOwnerId(userId);
//
////            System.out.println("workType: " + user.getWorkType());
//            //声明一个Map<String, Object>数组类型的response对象
//            Map<String, Object>[] response = new HashMap[vehicles.size()];
//            for (int i = 0; i < vehicles.size(); i++){
//                response[i] = new HashMap<>();  // 初始化每个Map
//                response[i].put("id", vehicles.get(i).getId());
//                response[i].put("owner", vehicles.get(i).getOwner());
//                response[i].put("licensePlate", vehicles.get(i).getLicensePlate());
//                response[i].put("brand", vehicles.get(i).getBrand());
//                response[i].put("color", vehicles.get(i).getColor());
//                response[i].put("year", vehicles.get(i).getYear());
//                response[i].put("model", vehicles.get(i).getModel());
//            }
//
//            System.out.println("User found: " + response);
//            return ResponseEntity.ok(response);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body("其他错误: " + e.getMessage());
//        }
    }
}