package com.atharvaworks.novelnook.service;

import com.atharvaworks.novelnook.dto.BookQuantity;
import com.atharvaworks.novelnook.dto.orderandcart.AddCartDto;
import com.atharvaworks.novelnook.exception.BadRequestException;
import com.atharvaworks.novelnook.model.Book;
import com.atharvaworks.novelnook.model.Cart;
import com.atharvaworks.novelnook.model.User;
import com.atharvaworks.novelnook.repository.BookRepository;
import com.atharvaworks.novelnook.repository.CartRepository;
import com.atharvaworks.novelnook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    public String addCart(AddCartDto addCartDto) {
        User user = userRepository.findByEmail(addCartDto.getEmail());
        if(null == user)
            throw new BadRequestException("User does not exist");

        Cart cart = new Cart();
        cart = cartRepository.findByEmail(addCartDto.getEmail());
        if(null == cart){
            cart = createNewCart(addCartDto);
        }else
            updateCart(addCartDto , cart);
        cartRepository.save(cart);
        return  "Cart Added successfully";

    }

    private void updateCart(AddCartDto addCartDto ,Cart cart) {
        List <BookQuantity> bookQuantityList = new ArrayList<>();
        bookQuantityList.addAll(addCartDto.getBookQuantity());
        for(BookQuantity bookQuantity : cart.getBookQuantity()){
            if(!bookQuantityList.contains(bookQuantity)){
                bookQuantityList.add(bookQuantity);
            }
        }
//        bookQuantityList.addAll(addCartDto.getBookQuantity());
        cart.setBookQuantity(bookQuantityList);
        List<Long> bookIdList = new ArrayList<>() ;
        Map<Long,Book> map;
        int sum =0;
        for(BookQuantity bookQuantity : bookQuantityList)
        {
            bookIdList.add(bookQuantity.getBookId());
        }
        List<Book> bookList = bookRepository.findAllById(bookIdList);
        map = bookList.stream().collect(Collectors.toMap(Book :: getBookId ,obj-> obj));
        for(BookQuantity bookQuantity : bookQuantityList){
            Book book = map.get(bookQuantity.getBookId());
            if(book == null)
                throw new BadRequestException("Book does not exist" );
            if(book.getBookQuantity()< bookQuantity.getQuantity())
                throw new BadRequestException("Book quantity is more than the available quantity");
            sum = sum + ( Integer.parseInt(book.getBookPrice()) * bookQuantity.getQuantity());
        }
        cart.setPrice(Integer.toString(sum));
    }

    private Cart createNewCart(AddCartDto addCartDto ) {
        Cart cart = new Cart();
        cart.setEmail(addCartDto.getEmail());
        cart.setBookQuantity(addCartDto.getBookQuantity());
        List<Long> bookIdList = new ArrayList<>() ;
        Map<Long,Book> map;
        int sum =0;
        for(BookQuantity bookQuantity : addCartDto.getBookQuantity())
        {
            bookIdList.add(bookQuantity.getBookId());
        }
        List<Book> bookList = bookRepository.findAllById(bookIdList);
        map = bookList.stream().collect(Collectors.toMap(Book :: getBookId ,obj-> obj));
        for(BookQuantity bookQuantity : addCartDto.getBookQuantity()){
            Book book = map.get(bookQuantity.getBookId());
            if(book == null)
                throw new BadRequestException("Book does not exist" );
            if(book.getBookQuantity() < bookQuantity.getQuantity())
                throw new BadRequestException("Book quantity is more than the available quantity");
            sum = sum + ( Integer.parseInt(book.getBookPrice()) * bookQuantity.getQuantity());
        }
        cart.setPrice(Integer.toString(sum));
        return cart;
    }
}