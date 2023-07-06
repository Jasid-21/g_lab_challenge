package com.reservemicroservice.reservemicroservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReserveDto {
    @NotBlank(message = "The name field is required")
    private String name;

    @NotBlank(message = "The email field is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "The document type field is required")
    private String docType;

    @NotBlank(message = "The document number field is required")
    private String docNumber;

    @NotNull(message = "The reserve date field is required")
    private LocalDateTime reserveDate;

    @NotBlank(message = "The event type field is required")
    private String eventType;

    @Min(value = 1, message = "The number of people must be greater than or equal to 1")
    private int people;

    private String observations;
}
