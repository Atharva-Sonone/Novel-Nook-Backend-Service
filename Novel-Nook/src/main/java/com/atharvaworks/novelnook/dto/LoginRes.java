package com.atharvaworks.novelnook.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRes {
    private String message;
    private String userName;
    private String email;
    private String role;
}
