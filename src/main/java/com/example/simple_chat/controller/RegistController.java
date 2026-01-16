package com.example.simple_chat.controller;

import com.example.simple_chat.dto.RegistResponseDto;
import com.example.simple_chat.enums.AuthStatus;
import com.example.simple_chat.repository.UserRepository;
import com.example.simple_chat.services.RegistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regist")
public class RegistController {
    private RegistServiceImpl registService;

    @Autowired
    public RegistController(UserRepository userRepository, RegistServiceImpl registService){
        this.registService = registService;

    }

    @PostMapping
    ResponseEntity<RegistResponseDto> register(@RequestBody RegistResponseDto registResponseDto){
        try {
            AuthStatus authStatus = registService.postRegistToCheck(registResponseDto);

            switch (authStatus){
                case CREATED -> {
                    return new ResponseEntity<>(registResponseDto, HttpStatus.CREATED);
                } case CONFLICT -> {
                    return new ResponseEntity<>(registResponseDto, HttpStatus.CONFLICT);
                }
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(registResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(registResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
