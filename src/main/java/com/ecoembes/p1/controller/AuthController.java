package com.ecoembes.p1.controller;

import org.springframework.web.bind.annotation.*;

import com.AppServices.AuthService;
import com.dto.LoginDTO;
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

        // 🔑 El servicio devuelve el token
        String token = authService.login(email, password);

        if (token == null) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        // Obtener usuario a partir del token
        Empleado emp = authService.obtenerUsuarioDesdeToken(token);

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




