package com.atharvaworks.novelnook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangePasswordReq {
    @Email(message = "Please enter valid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String oldPassword;
    @NotBlank(message = "Password cannot be empty")
    private String newPassword;

}
