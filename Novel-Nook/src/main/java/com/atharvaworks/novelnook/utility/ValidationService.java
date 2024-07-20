package com.atharvaworks.novelnook.utility;

import com.atharvaworks.novelnook.exception.BadRequestException;
import com.atharvaworks.novelnook.model.User;
import com.atharvaworks.novelnook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidationService {
    @Autowired
    UserRepository userRepository;
    public void userValidation(String email){
        User user = userRepository.findByEmail(email);
        if (null == user)
            throw new BadRequestException("User does not exist");
    }
}
