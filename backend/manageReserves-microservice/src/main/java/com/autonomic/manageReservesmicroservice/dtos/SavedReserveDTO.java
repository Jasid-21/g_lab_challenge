package com.autonomic.manageReservesmicroservice.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SavedReserveDTO {
    private Long id;
    private String name;
    private String email;
    private String docType;
    private String docNumber;
    private LocalDateTime reserveDate;
    private String reserveType;
    private int people;
    private String observations;
}
