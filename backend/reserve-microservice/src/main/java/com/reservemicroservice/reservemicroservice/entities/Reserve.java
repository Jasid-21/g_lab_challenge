package com.reservemicroservice.reservemicroservice.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserves")
@Data
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String docType;

    private String docNumber;

    private LocalDateTime reserveDate;

    private String reserveType;

    private int people;

    private String observations;
    private Boolean confirmed;

    // Constructor vacío (obligatorio para JPA)
    public Reserve() {
        this.confirmed = false;
    }

    // Constructor con todos los campos
    public Reserve(String name, String email, String docType, String docNumber, LocalDateTime reserveDate,
                   String reserveType, int people, String observations) {
        this.name = name;
        this.email = email;
        this.docType = docType;
        this.docNumber = docNumber;
        this.reserveDate = reserveDate;
        this.reserveType = reserveType;
        this.people = people;
        this.observations = observations;
        this.confirmed = false;
    }
}