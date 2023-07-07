package com.autonomic.manageReservesmicroservice.controllers;

import com.autonomic.manageReservesmicroservice.dtos.SavedReserveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/manage_reserve")
public class ManageReserves {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<SavedReserveDTO>> getAllReserves() {
        String url = "http://localhost:8081/reserve";
        ResponseEntity<List<SavedReserveDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SavedReserveDTO>>() {}
        );
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavedReserveDTO> updateReserve(@PathVariable Long id, @RequestBody SavedReserveDTO reserveDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SavedReserveDTO> requestEntity = new HttpEntity<>(reserveDTO, headers);
        ResponseEntity<SavedReserveDTO> response = restTemplate.exchange(
                "http://localhost:8081/reserve/{id}",
                HttpMethod.PUT,
                requestEntity,
                SavedReserveDTO.class,
                id
        );

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
