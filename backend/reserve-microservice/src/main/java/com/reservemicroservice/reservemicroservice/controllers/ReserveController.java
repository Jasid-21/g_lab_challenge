package com.reservemicroservice.reservemicroservice.controllers;

import com.reservemicroservice.reservemicroservice.dtos.ReserveDTO;
import com.reservemicroservice.reservemicroservice.dtos.SavedReserveDTO;
import com.reservemicroservice.reservemicroservice.entities.Reserve;
import com.reservemicroservice.reservemicroservice.services.ReserveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserve")
@Validated
public class ReserveController {
    @Autowired
    ReserveService reserveService;
    @PostMapping
    public ResponseEntity<String> requestReserve(@Valid @RequestBody ReserveDTO reserve) {
        String message = reserveService.saveReserve(reserve);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<SavedReserveDTO>> getAllReserves() {
        List<SavedReserveDTO> reserves = reserveService.getAllReserves();
        return ResponseEntity.ok(reserves);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavedReserveDTO> updateReserve(@PathVariable Long id, @RequestBody ReserveDTO reserveDto) {
        SavedReserveDTO updatedReserve = reserveService.updateReserve(id, reserveDto);
        return ResponseEntity.ok(updatedReserve);
    }
}
