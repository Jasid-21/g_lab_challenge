package com.autonomic.gateway.controllers;

import com.autonomic.gateway.dtos.ReserveDTO;
import com.autonomic.gateway.dtos.SavedReserveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/reserve")
@CrossOrigin(origins = "*")
public class ReserveController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<String> requestReserve(@RequestBody ReserveDTO reserveDTO) {
        String url = "http://localhost:8081/reserve";
        ResponseEntity<String> response = restTemplate.postForEntity(url, reserveDTO, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getReserve(@PathVariable Long id) {
        String url = "http://localhost:8081/reserve/" + id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping
    public ResponseEntity<List<SavedReserveDTO>> getAllReserves() {
        String url = "http://localhost:8083/manage_reserve";
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
                "http://localhost:8083/manage_reserve/{id}",
                HttpMethod.PUT,
                requestEntity,
                SavedReserveDTO.class,
                id
        );

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
