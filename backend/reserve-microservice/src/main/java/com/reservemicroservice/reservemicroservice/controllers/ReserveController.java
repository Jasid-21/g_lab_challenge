package com.reservemicroservice.reservemicroservice.controllers;

import com.reservemicroservice.reservemicroservice.dtos.ReserveDto;
import com.reservemicroservice.reservemicroservice.services.ReserveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
@Validated
public class ReserveController {
    @Autowired
    ReserveService reserveService;
    @PostMapping
    public ResponseEntity<String> requestReserve(@Valid @RequestBody ReserveDto reserve) {
        String message = reserveService.saveReserve(reserve);
        return ResponseEntity.ok(message);
    }
}
