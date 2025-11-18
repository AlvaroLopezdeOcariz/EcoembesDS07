package com.ecoembes.p1.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CapacidadPlantaDTO;
import com.facade.Facade;

@RestController
@RequestMapping("/plantas")
public class PlantasController {

    private final Facade facade;

    public PlantasController(Facade facade) {
        this.facade = facade;
    }

    @GetMapping("/capacidad")
    public CapacidadPlantaDTO capacidad(@RequestParam Integer idPlanta,
                                        @RequestParam Date fecha,
                                        @RequestHeader("Authorization") String token) {
        return facade.consultarCapacidadPlantaDia(idPlanta, fecha, token);
    }
}

