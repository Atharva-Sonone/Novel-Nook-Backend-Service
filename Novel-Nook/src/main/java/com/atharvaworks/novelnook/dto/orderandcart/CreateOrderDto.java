package com.atharvaworks.novelnook.dto.orderandcart;

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
public class CreateOrderDto {
    @Email(message = "Please enter valid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "List cannot be empty")
    private List<BookQuantity> bookQuantity;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Payment mode cannot be empty")
    private  String paymentMode;
}
