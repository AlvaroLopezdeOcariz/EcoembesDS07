package com.ecoembes.p1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoginDTO;
import com.dto.LoginRequest;
import com.dto.LogoutRequest;
import com.facade.Facade;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Facade facade;

    public AuthController(Facade facade) {
        this.facade = facade;
    }

    @PostMapping("/login")
    public LoginDTO login(@RequestBody LoginRequest req) {
    	
        return facade.login(req.getEmail(), req.getPassword());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequest req) {
        facade.logout(req.getToken());
    }
}

