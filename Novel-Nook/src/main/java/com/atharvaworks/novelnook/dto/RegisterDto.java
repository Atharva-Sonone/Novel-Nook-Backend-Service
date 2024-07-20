package com.atharvaworks.novelnook.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDto {
    @NotBlank(message = "Full Name cannot be empty")
    @Size(max = 25 , message = "Full name cannot exceed 25 Character")
    private String userName;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please enter valid email")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    private String address;
    @NotBlank(message = "Role cannot be empty")
    private String role;
//    @NotBlank(message = "Date of Birth cannot be empty")
    private LocalDate dob;
}
