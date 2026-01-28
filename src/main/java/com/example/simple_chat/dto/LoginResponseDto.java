package com.example.simple_chat.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResponseDto {
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Username must have only latin letters (A-Z, a-z) and numbers")
    @Length(min = 4, max = 50, message = "Name must have at least 2 characters and can't have more than 50 characters")
    public String username;

    @NotBlank
    @Length(min = 8, max = 50, message = "Password must have at least 8 characters and can't have more than 50 characters")
    public String password;
}
