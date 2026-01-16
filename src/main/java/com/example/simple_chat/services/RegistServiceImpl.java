package com.example.simple_chat.services;

import com.example.simple_chat.dto.RegistResponseDto;
import com.example.simple_chat.entity.User;
import com.example.simple_chat.enums.AuthStatus;
import com.example.simple_chat.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistServiceImpl implements  RegistService{
    private UserRepository userRepository;
    public RegistServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Transactional
    @Override
    public AuthStatus postRegistToCheck(RegistResponseDto registResponseDto) {
       return userRepository.findByUsername(registResponseDto.getUsername())
                .map(user ->  AuthStatus.CONFLICT)
                .orElseGet( () -> {
                    userRepository.save(
                            User.builder()
                                    .username(registResponseDto.getUsername())
                                    .password(registResponseDto.getPassword())
                                    .build()
                    );
                    return AuthStatus.CREATED;
                        }
                );

    }
}
