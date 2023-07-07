package com.admin_authmicroservice.admin_authmicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(name = "role_id")
    private Long roleId;

    // Constructor, getters y setters
    // ...
}

