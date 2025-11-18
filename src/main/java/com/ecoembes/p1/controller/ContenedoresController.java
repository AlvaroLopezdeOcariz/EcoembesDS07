package com.ecoembes.p1.controller;


import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ContenedorHistorialDTO;
import com.dto.CrearContenedorDTO;
import com.entity.Contenedor;
import com.facade.Facade;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/contenedores")
public class ContenedoresController {

    private final Facade facade;

    public ContenedoresController(Facade facade) {
        this.facade = facade;
    }

    @PostMapping
    public Contenedor crear(@RequestBody CrearContenedorDTO dto,
                            @RequestHeader("Authorization") String token) {
        return facade.crearContenedor(dto, token);
    }

    @GetMapping("/{id}/status")
    public ContenedorHistorialDTO status(@PathVariable String id,
                                         @RequestParam Date inicio,
                                         @RequestParam Date fin,
                                         @RequestHeader("Authorization") String token) {
        return facade.consultarContenedor(id, inicio, fin, token);
    }
}
