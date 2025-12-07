package com.ecoembes.p1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoginDTO;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.AppServices.AuthService;
import com.entity.Empleado;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

 
    @PostMapping("/login")
    public LoginDTO login(@RequestParam("email") String email,
                          @RequestParam("password") String password) {

        Empleado emp = authService.login(email, password);
        if (emp == null) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        // generar token
        String token = UUID.randomUUID().toString();

        

        return new LoginDTO(token, emp.getId(), emp.getEmail());
    }

    
    @PostMapping("/logout")
    public void logout(@RequestParam("token") String token) {

        validarToken(token);
        authService.logout(token);
    }

   
    private void validarToken(String token) {

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token obligatorio");
        }

        if (!authService.esTokenValido(token)) {
            throw new RuntimeException("Token inválido");
        }
    }
}



