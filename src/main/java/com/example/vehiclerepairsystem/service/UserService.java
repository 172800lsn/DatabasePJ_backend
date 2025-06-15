package com.example.vehiclerepairsystem.service;

import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public User findByUsername(String username,String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("用户未找到"));

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new IllegalArgumentException("密码错误");
        }
        return user;
    }
    // 根据用户名查找用户
    public User findByUsernameReally(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("用户未找到"));
    }
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("用户不存在！");
        }
        userRepository.deleteById(userId);
    }

    public User updateUser(Long userId, User updatedUser) {
        // 查找用户是否存在
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));

        // 更新用户信息
        if (updatedUser.getUsername() != null) {
            if (!existingUser.getUsername().equals(updatedUser.getUsername())
                    && userRepository.existsByUsername(updatedUser.getUsername())) {
                throw new IllegalArgumentException("用户名已被占用");
            }
            existingUser.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        // 更新其他必要字段（例如角色）
        if (updatedUser.getRole() != null) {
            existingUser.setRole(updatedUser.getRole());
        }

        return userRepository.save(existingUser);
    }


}
