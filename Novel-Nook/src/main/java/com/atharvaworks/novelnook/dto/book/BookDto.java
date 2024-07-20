package com.atharvaworks.novelnook.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long bookId;
    private String bookName;
    private String bookDescription;
    private String bookPrice;
    private String bookAuthor;
    private String category;
    private int bookQuantity;
}
