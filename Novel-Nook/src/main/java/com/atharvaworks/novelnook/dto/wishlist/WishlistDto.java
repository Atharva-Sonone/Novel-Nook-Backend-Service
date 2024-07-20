package com.atharvaworks.novelnook.dto.wishlist;

import com.atharvaworks.novelnook.dto.book.BookDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WishlistDto {
    List <BookDto> bookDtoList;
}
