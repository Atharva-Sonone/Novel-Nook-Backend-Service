package com.atharvaworks.novelnook.controller;

import com.atharvaworks.novelnook.dto.orderandcart.AddCartDto;
import com.atharvaworks.novelnook.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add-cart")
    ResponseEntity<String> addCart(@Valid @RequestBody AddCartDto addCartDto){
        return new ResponseEntity<>(cartService.addCart(addCartDto), HttpStatus.CREATED);
    }
}
