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

    @PutMapping("/update")
    public ResponseEntity<SavedReserveDTO> updateReserve(@RequestBody SavedReserveDTO reserveDTO) {
        System.out.println("Hello");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SavedReserveDTO> requestEntity = new HttpEntity<>(reserveDTO, headers);
        ResponseEntity<SavedReserveDTO> response = restTemplate.exchange(
                "http://localhost:8081/reserve/update",
                HttpMethod.PUT,
                requestEntity,
                SavedReserveDTO.class
        );

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
