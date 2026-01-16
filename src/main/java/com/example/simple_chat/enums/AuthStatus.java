package com.example.simple_chat.enums;

public enum AuthStatus {
    CREATED,            //акк создан
    SUCCESS,            //получилось войти или зарегатся
    INVALID_DATA,       //чета не то
    USER_NOT_FOUND,
    UNAUTHORIZED,       //пароль (данные пользователя кроме username) неверные
    CONFLICT            //такой юзернейм занят
}

