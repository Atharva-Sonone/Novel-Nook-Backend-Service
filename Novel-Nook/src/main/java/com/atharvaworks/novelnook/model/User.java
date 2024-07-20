package com.atharvaworks.novelnook.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "user")
public class User {
    @Id
    private ObjectId userId;
    private String userName;
    private String email;
    private String password;
    private LocalDate dob;
    private String role;
    private String address;

}
