package com.atharvaworks.novelnook.service;

import com.atharvaworks.novelnook.dto.book.BookDto;
import com.atharvaworks.novelnook.dto.wishlist.CreateWishlistDto;
import com.atharvaworks.novelnook.dto.wishlist.WishlistDto;
import com.atharvaworks.novelnook.exception.BadRequestException;
import com.atharvaworks.novelnook.model.Book;
import com.atharvaworks.novelnook.model.User;
import com.atharvaworks.novelnook.model.WishList;
import com.atharvaworks.novelnook.repository.BookRepository;
import com.atharvaworks.novelnook.repository.UserRepository;
import com.atharvaworks.novelnook.repository.WishlistRepository;
import com.atharvaworks.novelnook.utility.DtoMapping;
import com.atharvaworks.novelnook.utility.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    WishlistRepository wishlistRepository;
    @Autowired
    ValidationService validationService;
    @Autowired
    DtoMapping dtoMapping;
    public String createWishlist(CreateWishlistDto createWishlistDto) {
        validationService.userValidation(createWishlistDto.getEmail());

        WishList wishList = new WishList();
        wishList = wishlistRepository.findByEmail(createWishlistDto.getEmail());
        List<Long> wishlistList = new ArrayList<>(createWishlistDto.getBookId());
        List<Long> finalwishlist = new ArrayList<>();
        if(!CollectionUtils.isEmpty(wishList.getBookList())){
            wishlistList.addAll(wishList.getBookList());
        }
        for(Long l : wishlistList)
        {
            if(!finalwishlist.contains(l))
                finalwishlist.add(l);
        }
        wishList.setBookList(finalwishlist);
        wishlistRepository.save(wishList);
        return "WishList Created";

    }

    public WishlistDto getWishlistByEmail(String email){
        validationService.userValidation(email);
        WishList wishList = new WishList();
        WishlistDto wishlistDto = new WishlistDto();
        wishList = wishlistRepository.findByEmail(email);
        if(!CollectionUtils.isEmpty(wishList.getBookList())){
            List<Book> bookList = bookRepository.findAllByBookId(wishList.getBookList());
            List<BookDto> bookDtoList = new ArrayList<>();
            for(Book book : bookList){
                BookDto bookDto =dtoMapping.modelToBookDto(book);
                bookDtoList.add(bookDto);
            }
            wishlistDto.setBookDtoList(bookDtoList);
        }
        else {
            throw new BadRequestException("Wishlist is empty");
        }
        return wishlistDto;
    }
}
