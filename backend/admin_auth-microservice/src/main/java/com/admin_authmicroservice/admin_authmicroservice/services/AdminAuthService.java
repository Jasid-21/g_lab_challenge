package com.admin_authmicroservice.admin_authmicroservice.services;

import com.admin_authmicroservice.admin_authmicroservice.entities.Role;
import com.admin_authmicroservice.admin_authmicroservice.entities.User;
import com.admin_authmicroservice.admin_authmicroservice.repositories.RoleRepository;
import com.admin_authmicroservice.admin_authmicroservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public String authenticateAdmin(String email, String password) {
        User adminUser = userRepository.findByEmailAndPassword(email, password);
        if (adminUser != null) {
            Role adminRole = roleRepository.findById(adminUser.getRoleId()).orElse(null);
            if (adminRole != null && adminRole.getName().equals("Admin")) {
                return adminRole.getName();
            }
        }
        return null;
    }
}

