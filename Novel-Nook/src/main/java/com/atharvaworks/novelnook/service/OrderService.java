package com.atharvaworks.novelnook.service;

import com.atharvaworks.novelnook.dto.BookQuantity;
import com.atharvaworks.novelnook.dto.orderandcart.CreateOrderDto;
import com.atharvaworks.novelnook.dto.orderandcart.OrderResDto;
import com.atharvaworks.novelnook.exception.BadRequestException;
import com.atharvaworks.novelnook.model.Book;
import com.atharvaworks.novelnook.model.Cart;
import com.atharvaworks.novelnook.model.Order;
import com.atharvaworks.novelnook.model.User;
import com.atharvaworks.novelnook.repository.BookRepository;
import com.atharvaworks.novelnook.repository.OrderRepository;
import com.atharvaworks.novelnook.repository.UserRepository;
import com.atharvaworks.novelnook.utility.DtoMapping;
import com.atharvaworks.novelnook.utility.GenerateSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    GenerateSequence generateSequence;
    @Autowired
    DtoMapping dtoMapping;

    public String createOrder(CreateOrderDto createOrderDto) {
        User user = userRepository.findByEmail(createOrderDto.getEmail());
        if(null == user)
            throw new BadRequestException("User does not exist");

        Order order = new Order();
        order.setOrderId(generateSequence.generateSequence(Order.SEQUENCE_NAME));
        order.setEmail(createOrderDto.getEmail());
        order.setPaymentMode(createOrderDto.getPaymentMode());
        order.setAddress(createOrderDto.getAddress());
        order.setBookList(createOrderDto.getBookQuantity());

        List<Long> bookIdList = new ArrayList<>() ;
        Map<Long, Book> map;
        int sum =0;
        for(BookQuantity bookQuantity : createOrderDto.getBookQuantity())
        {
            bookIdList.add(bookQuantity.getBookId());
        }
        List<Book> bookList = bookRepository.findAllById(bookIdList);
        map = bookList.stream().collect(Collectors.toMap(Book :: getBookId , obj-> obj));
        for(BookQuantity bookQuantity : createOrderDto.getBookQuantity()){
            Book book = map.get(bookQuantity.getBookId());
            if(book == null)
                throw new BadRequestException("Book does not exist" );
            if(book.getBookQuantity()< bookQuantity.getQuantity())
                throw new BadRequestException("Book quantity is more than the available quantity");
            sum = sum + ( Integer.parseInt(book.getBookPrice()) * bookQuantity.getQuantity());
        }
        order.setPrice(Integer.toString(sum));

        orderRepository.save(order);
        return  "Order placed successfully";
    }

    public List<OrderResDto> viewAllOrders(String email) {
        User user = userRepository.findByEmail(email);
        if(null == user)
            throw new BadRequestException("User does not exist");

        List<Order> orderList = orderRepository.findByEmail(email);
        if(null == orderList)
            throw  new BadRequestException("No Orders From this email");
        List<OrderResDto> orderResDtoList = new ArrayList<>();
        for(Order order : orderList){
            OrderResDto orderResDto = dtoMapping.modelToorderResDto(order);
            orderResDtoList.add(orderResDto);
        }
        return orderResDtoList;
    }

    public OrderResDto viewOrderById(long id){
        Order order = orderRepository.findByOrderId(id);
        if(null == order)
        {
            throw new BadRequestException("Order Not found by the id");
        }
        return dtoMapping.modelToorderResDto(order);
    }
}
