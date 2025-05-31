package com.example.vehiclerepairsystem.controller;

import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "*") // 可根据需要限制来源
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
//        System.out.println("[DEBUG] 进入 register 控制器");
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok("用户注册成功 : " + registeredUser.getRole());
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // 打印已知异常
            return ResponseEntity.badRequest().body("注册失败: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // 打印未捕获异常
            return ResponseEntity.status(500).body("内部错误: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        try {
            User user = userService.findByUsername(username, password);
            System.out.println("User id: " + user.getId());
            System.out.println("workType: " + user.getWorkType());
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

    @GetMapping("/test")
    public String test() {
        return "Hello from backend!";
    }
}
