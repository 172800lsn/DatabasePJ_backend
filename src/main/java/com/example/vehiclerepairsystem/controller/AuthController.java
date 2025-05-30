package com.example.vehiclerepairsystem.controller;

import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("[DEBUG] 进入 register 控制器");
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully with role: " + registeredUser.getRole());
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // 打印已知异常
            return ResponseEntity.badRequest().body("注册失败: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // 打印未捕获异常
            return ResponseEntity.status(500).body("内部错误: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            User user = userService.findByUsername(username);
            // 登录逻辑可扩展
            return ResponseEntity.ok("Login successful for user: " + user.getUsername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Hello from backend!";
    }
}
