package com.example.simple_chat.services;

import com.example.simple_chat.reqClasses.AuthenticateUserRequest;

public interface AuthenticateUserService {
    boolean checkAuthenticateUser(String username);
}
