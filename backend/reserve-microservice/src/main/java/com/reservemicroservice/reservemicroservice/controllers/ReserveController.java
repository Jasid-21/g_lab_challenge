package com.reservemicroservice.reservemicroservice.controllers;

import com.reservemicroservice.reservemicroservice.dtos.ReserveDTO;
import com.reservemicroservice.reservemicroservice.dtos.SavedReserveDTO;
import com.reservemicroservice.reservemicroservice.services.ReserveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/reserve")
@Validated
public class ReserveController {
    @Autowired
    ReserveService reserveService;
    @PostMapping
    public ResponseEntity<String> requestReserve(@Valid @RequestBody ReserveDTO reserve) {
        try {
            String message = reserveService.saveReserve(reserve);
            return ResponseEntity.ok(message);
        } catch (HttpClientErrorException err) {
            HttpStatusCode status = err.getStatusCode();
            return  ResponseEntity.status(status.value()).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<SavedReserveDTO>> getAllReserves() {
        try {
            List<SavedReserveDTO> reserves = reserveService.getAllReserves();
            System.out.println(reserves);
            return ResponseEntity.ok(reserves);
        } catch (HttpClientErrorException err) {
            HttpStatusCode status = err.getStatusCode();
            return  ResponseEntity.status(status.value()).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SavedReserveDTO> updateReserve(@Valid @RequestBody SavedReserveDTO reserveDto) {
        try {
            SavedReserveDTO updatedReserve = reserveService.updateReserve(reserveDto);
            if (updatedReserve == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(updatedReserve);
        } catch (HttpClientErrorException err) {
            HttpStatusCode status = err.getStatusCode();
            return  ResponseEntity.status(status.value()).body(null);
        }
    }
}
