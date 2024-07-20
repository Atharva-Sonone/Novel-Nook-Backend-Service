package com.atharvaworks.novelnook.controller;

import com.atharvaworks.novelnook.dto.orderandcart.CreateOrderDto;
import com.atharvaworks.novelnook.dto.orderandcart.OrderResDto;
import com.atharvaworks.novelnook.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create_order")
    public ResponseEntity<String> createOrder(@Valid @RequestBody CreateOrderDto createOrderDto){
        return new ResponseEntity<String>(orderService.createOrder(createOrderDto) , HttpStatus.CREATED);
    }

    @GetMapping("/view_all_orders/{email}")
    public ResponseEntity<List<OrderResDto>> viewAllOrders(@PathVariable String email){
        return new ResponseEntity<List<OrderResDto>>(orderService.viewAllOrders(email) , HttpStatus.OK);
    }

    @GetMapping("/view_order_by_id/{id}")
    public  ResponseEntity<OrderResDto> viewOrderById(@PathVariable long id){
        return new ResponseEntity<OrderResDto>(orderService.viewOrderById(id), HttpStatus.OK);
    }
}
