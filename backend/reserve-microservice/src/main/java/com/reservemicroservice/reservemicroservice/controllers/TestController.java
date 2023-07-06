package com.reservemicroservice.reservemicroservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public ResponseEntity<String> handleTest() {
        return ResponseEntity.ok("Hello world!!");
    }
}
