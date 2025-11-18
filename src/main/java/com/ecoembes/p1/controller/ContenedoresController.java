package com.ecoembes.p1.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.dto.ContenedorHistorialDTO;
import com.dto.CrearContenedorDTO;
import com.entity.Contenedor;
import com.facade.Facade;

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
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fin,
                                         @RequestHeader("Authorization") String token) {
        return facade.consultarContenedor(id, inicio, fin, token);
    }
}

