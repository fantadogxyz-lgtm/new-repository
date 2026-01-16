package com.example.simple_chat.services;

import com.example.simple_chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserServiceImpl implements AuthenticateUserService{
    private UserRepository userRepository;

    @Autowired
    AuthenticateUserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public boolean checkAuthenticateUser(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
