package com.atharvaworks.novelnook.dto.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddAddressDto {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please enter valid email")
    private String email;
    @NotBlank(message = "Address cannot be empty")
    private String address;
}
