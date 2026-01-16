package com.example.simple_chat.controller;


import com.example.simple_chat.services.AuthenticateUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private final AuthenticateUserServiceImpl authenticateUserService;

    @Autowired
    PageController(AuthenticateUserServiceImpl authenticateUserService){
        this.authenticateUserService = authenticateUserService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/chat") //тут попытка не пускать неавторизованного пользователя при попытке запроса http://localhost:8080/chat, но в теории не мешает в ручную записать запрос :_). Хотя еще на фронте не пускает
    public String chat(@RequestParam(required = false) String username, @RequestParam (required = false) String auth){
        if(!"true".equals(auth)) return "redirect:/";
        if(authenticateUserService.checkAuthenticateUser(username)) return "chat";
        return "redirect:/";
    }

    @GetMapping("/auth")
    public String authPage(){
        return "auth";
    }
}
