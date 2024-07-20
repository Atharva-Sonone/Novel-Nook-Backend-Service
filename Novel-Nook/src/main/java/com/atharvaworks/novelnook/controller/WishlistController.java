package com.atharvaworks.novelnook.controller;

import com.atharvaworks.novelnook.dto.wishlist.CreateWishlistDto;
import com.atharvaworks.novelnook.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @PostMapping("/create")
    public ResponseEntity<String> createWishlist(@Valid @RequestBody CreateWishlistDto createWishlistDto){
        return new ResponseEntity<String>(wishlistService.createWishlist(createWishlistDto), HttpStatus.CREATED);
    }

}
