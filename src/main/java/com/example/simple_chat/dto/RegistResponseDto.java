package com.example.simple_chat.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegistResponseDto {
    @NotEmpty
    @Length(min = 4, max = 50)
    public String username;
    @NotEmpty
    @Length(min = 8, max = 50)
    public String password;
}
