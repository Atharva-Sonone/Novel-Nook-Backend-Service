package com.atharvaworks.novelnook.controller;

import com.atharvaworks.novelnook.dto.ChangePasswordReq;
import com.atharvaworks.novelnook.dto.LoginReq;
import com.atharvaworks.novelnook.dto.LoginRes;
import com.atharvaworks.novelnook.dto.RegisterDto;
import com.atharvaworks.novelnook.dto.profile.AddAddressDto;
import com.atharvaworks.novelnook.dto.profile.ProfileRes;
import com.atharvaworks.novelnook.service.LoginService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) throws BadRequestException {
        return new ResponseEntity<String>(loginService.register(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@Valid @RequestBody LoginReq loginReq) throws BadRequestException {
        return new ResponseEntity<>(loginService.login(loginReq),HttpStatus.OK);
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordReq changePasswordReq) throws BadRequestException {
        return new ResponseEntity<String>(loginService.changePassword(changePasswordReq), HttpStatus.OK);
    }
    @GetMapping("/view-profile/{email}")
    public  ResponseEntity<ProfileRes> viewProfile(@PathVariable String email){
        return new ResponseEntity<ProfileRes>(loginService.viewProfile(email),HttpStatus.OK);
    }

    @PostMapping("/add_address")
    public ResponseEntity<String> addAddress(@Valid @RequestBody AddAddressDto addAddressDto){
        return new ResponseEntity<String>(loginService.addAddress(addAddressDto), HttpStatus.OK);
    }
}
