package com.example.simple_chat.services;

import com.example.simple_chat.dto.LoginResponseDto;
import com.example.simple_chat.dto.RegistResponseDto;
import com.example.simple_chat.enums.AuthStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginService {
    public AuthStatus postLoginToCheck(@RequestBody LoginResponseDto loginResponseDto);
}
