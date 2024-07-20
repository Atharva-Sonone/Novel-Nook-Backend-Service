package com.atharvaworks.novelnook.utility;

import com.atharvaworks.novelnook.dto.RegisterDto;
import com.atharvaworks.novelnook.dto.book.AddBookDto;
import com.atharvaworks.novelnook.dto.book.BookDto;
import com.atharvaworks.novelnook.dto.orderandcart.OrderResDto;
import com.atharvaworks.novelnook.dto.profile.ProfileRes;
import com.atharvaworks.novelnook.model.Book;
import com.atharvaworks.novelnook.model.Order;
import com.atharvaworks.novelnook.model.User;
import org.springframework.stereotype.Service;

@Service
public class DtoMapping {
    public User registerDtoToModel(RegisterDto registerDto){
        User user = new User();
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setRole(registerDto.getRole());
        user.setDob(registerDto.getDob());
        user.setAddress(registerDto.getAddress());
        return user;
    }
    public Book addBookDtoToModel(AddBookDto addBookDto){
        Book book =new Book();
        book.setBookName(addBookDto.getBookName());
        book.setBookAuthor(addBookDto.getBookAuthor());
        book.setBookDescription(addBookDto.getBookDescription());
        book.setBookPrice(addBookDto.getBookPrice());
        book.setCategory(addBookDto.getCategory());
        book.setBookQuantity(addBookDto.getBookQuantity());
        return book;
    }
    public AddBookDto modelToaddBookDto(Book book){
        AddBookDto addBookDto = new AddBookDto();
        addBookDto.setBookName(book.getBookName());
        addBookDto.setBookAuthor(book.getBookAuthor());
        addBookDto.setBookDescription(book.getBookDescription());
        addBookDto.setBookPrice(book.getBookPrice());
        addBookDto.setCategory(book.getCategory());
        return addBookDto;
    }
    public BookDto modelToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getBookId());
        bookDto.setBookName(book.getBookName());
        bookDto.setBookAuthor(book.getBookAuthor());
        bookDto.setBookDescription(book.getBookDescription());
        bookDto.setBookPrice(book.getBookPrice());
        bookDto.setCategory(book.getCategory());
        bookDto.setBookQuantity(book.getBookQuantity());
        return bookDto;
    }

    public ProfileRes modelToProfileRes(User user) {
        ProfileRes profileRes = new ProfileRes();
        profileRes.setUserName(user.getUserName());
        profileRes.setEmail(user.getEmail());
        profileRes.setDob(user.getDob());
        profileRes.setAddress(user.getAddress());
        return profileRes;
    }

    public OrderResDto modelToorderResDto(Order order) {
        OrderResDto orderResDto =new OrderResDto();
        orderResDto.setId(order.getOrderId());
        orderResDto.setEmail(order.getEmail());
        orderResDto.setBookQuantity(order.getBookList());
        orderResDto.setPrice(order.getPrice());
        orderResDto.setAddress(order.getAddress());
        orderResDto.setPaymentMode(order.getPaymentMode());
        return orderResDto;
    }
}
