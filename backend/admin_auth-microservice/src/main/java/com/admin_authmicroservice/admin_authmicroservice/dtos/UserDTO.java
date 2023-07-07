package com.admin_authmicroservice.admin_authmicroservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "The email field is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "The email field is required")
    @Email(message = "Invalid email format")
    private String password;
}
