package com.example.simple_chat.services;

import com.example.simple_chat.dto.LoginResponseDto;
import com.example.simple_chat.enums.AuthStatus;
import com.example.simple_chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LoginServiceImpl implements LoginService{
    private UserRepository userRepository;

    @Autowired
    LoginServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public AuthStatus postLoginToCheck(@RequestBody LoginResponseDto loginResponseDto){
        return userRepository.findByUsername(loginResponseDto.getUsername())
                .map(user -> {
                    if (loginResponseDto.getPassword().equals(user.getPassword())){
                        return  AuthStatus.SUCCESS;
                    }
                    else{
                       return AuthStatus.INVALID_DATA;
                    }
                }).orElseGet( () -> {
                    return AuthStatus.USER_NOT_FOUND;
                }
                );

    }

}
