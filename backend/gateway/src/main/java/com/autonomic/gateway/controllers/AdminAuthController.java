package com.autonomic.gateway.controllers;

import com.autonomic.gateway.config.JwtUtil;
import com.autonomic.gateway.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AdminAuthController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        try {
            String url = "http://localhost:8082/auth/login";
            ResponseEntity<String> response = restTemplate.postForEntity(url, userDTO, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String token = jwtUtil.generateToken(userDTO.getEmail(), response.getBody());
                return ResponseEntity.ok(token);
            } else {
                System.out.println(response.getStatusCode());
                return response;
            }
        } catch (HttpClientErrorException err) {
            HttpStatusCode status = err.getStatusCode();
            return ResponseEntity.status(status.value()).body(err.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok("Some information");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are unauthorized to do this...");
    }
}
