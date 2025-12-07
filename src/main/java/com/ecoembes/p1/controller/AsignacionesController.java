package com.ecoembes.p1.controller;

import org.springframework.web.bind.annotation.*;

import com.AppServices.AsignacionService;
import com.AppServices.AuthService;
import com.dto.AsignacionPlantaDTO;
import com.dto.AsignacionResultadoDTO;
import com.entity.Empleado;

@RestController
@RequestMapping("/asignaciones")
public class AsignacionesController {

    private final AsignacionService asignacionService;
    private final AuthService authService;

    public AsignacionesController(AsignacionService asignacionService,
                                  AuthService authService) {
        this.asignacionService = asignacionService;
        this.authService = authService;
    }

    @PostMapping
    public AsignacionResultadoDTO asignar(@RequestBody AsignacionPlantaDTO dto,
                                          @RequestParam String token) {

        validarToken(token);

        Empleado usuario = authService.obtenerUsuarioDesdeToken(token);

        return asignacionService.asignarContenedoresAPlanta(
                dto.getNombre(),
                dto.getListaContenedores(),
                usuario
        );
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

