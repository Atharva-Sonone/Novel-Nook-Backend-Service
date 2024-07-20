package com.atharvaworks.novelnook.dto.wishlist;

import com.atharvaworks.novelnook.dto.BookQuantity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateWishlistDto {
    @Email(message = "Please enter valid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "List cannot be empty")
    private List<Long> bookId;
}
