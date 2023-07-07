package com.admin_authmicroservice.admin_authmicroservice.controllers;

import com.admin_authmicroservice.admin_authmicroservice.dtos.UserDTO;
import com.admin_authmicroservice.admin_authmicroservice.services.AdminAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AdminController {
    @Autowired
    AdminAuthService adminAuthService;

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody UserDTO userDTO) {
        String responseRole = adminAuthService.authenticateAdmin(userDTO.getEmail(), userDTO.getPassword());
        if (responseRole != null) {
            return ResponseEntity.ok(responseRole);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
