package com.ecoembes.p1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AsignacionPlantaDTO;
import com.dto.AsignacionResultadoDTO;
import com.facade.Facade;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/asignaciones")
public class AsignacionesController {

    private final Facade facade;

    public AsignacionesController(Facade facade) {
        this.facade = facade;
    }

    @PostMapping
    public AsignacionResultadoDTO asignar(@RequestBody AsignacionPlantaDTO dto,
                                          @RequestHeader("Authorization") String token) {
        return facade.asignarContenedoresAPlanta(dto, token);
    }
}
