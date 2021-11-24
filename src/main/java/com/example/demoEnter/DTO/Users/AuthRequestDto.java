package com.example.demoEnter.DTO.Users;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthRequestDto {
    
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
