package com.myfirstproject.myfirstproject.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    
    @NotBlank(message = "O email não pode estar em branco.")
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco.")
    private String password;
}