package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        System.out.println("[DEBUG] 开始注册用户：" + user);

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("用户名已存在");
        }

        if (user.getRole() == null) {
            System.out.println("[WARN] 用户未设置角色，默认设为 USER");
            user.setRole(User.Role.USER);
        }

        if (user.getPassword() == null) {
            throw new IllegalArgumentException("密码不能为空");
        }

        System.out.println("[DEBUG] 加密密码前：" + user.getPassword());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println("[DEBUG] 加密密码后：" + encodedPassword);

        User savedUser = userRepository.save(user);
        System.out.println("[DEBUG] 注册成功：" + savedUser);

        return savedUser;
    }

    public User findByUsernameAndPassword(String username,String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new IllegalArgumentException("用户未找到"));
    }
}
