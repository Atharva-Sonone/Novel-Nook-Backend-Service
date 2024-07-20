package com.atharvaworks.novelnook.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookQuantity {

    private long bookId;
    private int quantity;
}
