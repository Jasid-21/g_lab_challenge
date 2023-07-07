package com.autonomic.gateway.controllers;

import com.autonomic.gateway.config.JwtUtil;
import com.autonomic.gateway.dtos.ReserveDTO;
import com.autonomic.gateway.dtos.SavedReserveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReserveController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<String> requestReserve(@RequestBody ReserveDTO reserveDTO) {
        try {
            String url = "http://localhost:8081/reserve";
            ResponseEntity<String> response = restTemplate.postForEntity(url, reserveDTO, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException err) {
            HttpStatusCode status = err.getStatusCode();
            return  ResponseEntity.status(status.value()).body(err.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<SavedReserveDTO>> getAllReserves(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if (jwtUtil.validateToken(token) == false) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String url = "http://localhost:8083/manage_reserve";
        ResponseEntity<List<SavedReserveDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SavedReserveDTO>>() {}
        );
        System.out.println(response.getBody());
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<SavedReserveDTO> updateReserve(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody SavedReserveDTO reserveDTO) {
        String token = authHeader.substring(7);
        if (jwtUtil.validateToken(token) == false) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SavedReserveDTO> requestEntity = new HttpEntity<>(reserveDTO, headers);
        ResponseEntity<SavedReserveDTO> response = restTemplate.exchange(
                "http://localhost:8083/manage_reserve/update",
                HttpMethod.PUT,
                requestEntity,
                SavedReserveDTO.class
        );

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
