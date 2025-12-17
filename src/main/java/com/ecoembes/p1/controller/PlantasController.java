package com.ecoembes.p1.controller;

import org.springframework.web.bind.annotation.*;

import com.AppServices.AuthService;
import com.AppServices.PlantaService;
import com.dto.CapacidadPlantaDTO;

@RestController
@RequestMapping("/plantas")
public class PlantasController {

    private final PlantaService plantaService;
    private final AuthService authService;

    public PlantasController(PlantaService plantaService,
                             AuthService authService) {
        this.plantaService = plantaService;
        this.authService = authService;
    }

    @GetMapping("/capacidad")
    public CapacidadPlantaDTO capacidad(
            @RequestParam("idPlanta") String idPlanta,
            @RequestParam("fecha") String fecha,
            @RequestParam("token") String token) {

        validarToken(token);
        return plantaService.consultarCapacidad(idPlanta, fecha);
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



