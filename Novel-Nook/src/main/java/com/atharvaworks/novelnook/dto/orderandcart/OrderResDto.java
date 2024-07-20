package com.atharvaworks.novelnook.dto.orderandcart;

import com.atharvaworks.novelnook.dto.BookQuantity;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResDto {
    private long id;
    private String email;
    private List<BookQuantity> bookQuantity;
    private String price;
    private String address;
    private String paymentMode;
}
