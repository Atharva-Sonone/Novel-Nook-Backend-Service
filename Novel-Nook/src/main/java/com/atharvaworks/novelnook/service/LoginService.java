package com.atharvaworks.novelnook.service;

import com.atharvaworks.novelnook.dto.ChangePasswordReq;
import com.atharvaworks.novelnook.dto.LoginReq;
import com.atharvaworks.novelnook.dto.LoginRes;
import com.atharvaworks.novelnook.dto.RegisterDto;
import com.atharvaworks.novelnook.dto.profile.AddAddressDto;
import com.atharvaworks.novelnook.dto.profile.ProfileRes;
import com.atharvaworks.novelnook.exception.BadRequestException;
import com.atharvaworks.novelnook.model.User;
import com.atharvaworks.novelnook.repository.UserRepository;
import com.atharvaworks.novelnook.utility.DtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    DtoMapping dtoMapping;

    @Autowired
    UserRepository userRepository;
    public String register(RegisterDto registerDto) throws BadRequestException {
        User existingUsers = userRepository.findByEmail(registerDto.getEmail());
        if(null != existingUsers){
            throw new BadRequestException("User already exists");
        }
        User user = dtoMapping.registerDtoToModel(registerDto);
        User savedUser = userRepository.save(user);
        if(null != savedUser.getUserName())
            return "Successfully registered";
        else
            return "Something went wrong";
    }
    public LoginRes login(LoginReq loginReq) throws BadRequestException {
        User user = userRepository.findByEmail(loginReq.getEmail());
        if(null != user){
            if(!loginReq.getPassword().equals(user.getPassword())){
                throw new BadRequestException("Incorrect Password");
            }
            else{
                LoginRes loginRes = new LoginRes();
                loginRes.setMessage("Login Successful");
                loginRes.setUserName(user.getUserName());
                loginRes.setRole(user.getRole());
                loginRes.setEmail(user.getEmail());
                return loginRes;
            }
        }else {
            throw new BadRequestException("User Not Available");
        }
    }
    public String changePassword(ChangePasswordReq changePasswordReq) throws BadRequestException {
        User user = userRepository.findByEmail(changePasswordReq.getEmail());
        if(null == user){
            throw new BadRequestException("User does not exist");
        }else {
            if(!changePasswordReq.getOldPassword().equals(user.getPassword())){
                throw new BadRequestException("Incorrect old password");
            }
            else{
                user.setPassword(changePasswordReq.getNewPassword());
                User savedUser = userRepository.save(user);
                if(null != savedUser.getUserName())
                    return "Password changed successfully";
                else
                    return "Something went wrong";
            }
        }
    }

    public ProfileRes viewProfile(String email) {
        User user = userRepository.findByEmail(email);
        if(null == user){
            throw new BadRequestException("User Does Not exists");
        }
        else{
            return dtoMapping.modelToProfileRes(user);
        }
    }

    public String addAddress(AddAddressDto addAddressDto) {
        User user = userRepository.findByEmail(addAddressDto.getEmail());
        if(null == user){
            throw new BadRequestException("User Does not exists");
        }
        user.setAddress(addAddressDto.getAddress());
        userRepository.save(user);
        return "Address Added Successfully";
    }
}
