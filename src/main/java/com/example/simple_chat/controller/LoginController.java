package com.example.simple_chat.controller;

import com.example.simple_chat.dto.LoginResponseDto;
import com.example.simple_chat.enums.AuthStatus;
import com.example.simple_chat.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginController {

    private LoginServiceImpl loginService;

    @Autowired
    LoginController(LoginServiceImpl loginService){
        this.loginService = loginService;
    }

    @PostMapping
    @Transactional
    ResponseEntity<LoginResponseDto> postLoginToCheck(@RequestBody @Validated LoginResponseDto loginResponseDto){
        try {
            AuthStatus authStatus = loginService.postLoginToCheck(loginResponseDto);
            switch (authStatus) {
                case SUCCESS -> {
                    return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
                }
                case INVALID_DATA -> {
                    return new ResponseEntity<>(loginResponseDto, HttpStatus.UNAUTHORIZED);
                }
                case USER_NOT_FOUND -> {
                    return new ResponseEntity<>(loginResponseDto, HttpStatus.NOT_FOUND);
                }

            }
        } catch (Exception e){
            return new ResponseEntity<>(loginResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loginResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
