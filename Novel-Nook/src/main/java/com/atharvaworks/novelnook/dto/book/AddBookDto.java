package com.atharvaworks.novelnook.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddBookDto {
    @NotBlank(message = "Book name is required")
    @Size(max = 25, message = "Book name cannot exceed 25 char")
    private String bookName;
    @NotBlank(message = "Book description is required")
    @Size(max = 255, message = "Book description cannot exceed 255 char")
    private  String bookDescription;
    @Pattern(regexp = "\\d*", message = "Only Digits are allowed")
    @NotBlank(message = "Book price is required")
    private String bookPrice;
    @NotBlank(message = "Book author is required")
    @Size(max = 25, message = "Book author cannot exceed 25 char")
    private String bookAuthor;
    @NotBlank(message = "Book category is required")
    private String category;
    @Min(message = "Book quantity can be less than 1", value = 1)
    private int bookQuantity;
}
