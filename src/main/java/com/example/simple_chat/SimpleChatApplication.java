package com.example.simple_chat;

import com.example.simple_chat.security.filter.DeniedClientFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

@SpringBootApplication
public class SimpleChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleChatApplication.class, args);
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(new DeniedClientFilter(), DisableEncodeUrlFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
