package com.ecoembes.p1.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.ContenedorHistorialDTO;
import com.dto.CrearContenedorDTO;
import com.entity.Contenedor;
import com.AppServices.ContenedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/contenedores")
@Tag(name = "Contenedores", description = "Gestión de contenedores inteligentes")
public class ContenedoresController {

    private final ContenedorService contenedorService;

    public ContenedoresController(ContenedorService contenedorService) {
        this.contenedorService = contenedorService;
    }



    @Operation(
        summary = "Crear un nuevo contenedor",
        description = "Crea un contenedor con los datos proporcionados"
    )
    @ApiResponse(responseCode = "201", description = "Contenedor creado correctamente")
    @PostMapping
    public ResponseEntity<Contenedor> crear(
            @RequestBody CrearContenedorDTO dto,

            @Parameter(description = "Token proporcionado por el usuario", required = false)
            @RequestParam(value = "token", required = false) String token) {

        Contenedor nuevo = contenedorService.crearContenedor(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Consultar estado del contenedor",
        description = "Obtiene el historial de estados del contenedor entre dos fechas"
    )
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    @ApiResponse(responseCode = "404", description = "Contenedor no encontrado")
    @GetMapping("/{id}/status")
    public ResponseEntity<ContenedorHistorialDTO> status(
            @Parameter(description = "ID del contenedor", example = "C123")
            @PathVariable String id,

            @Parameter(description = "Fecha de inicio (YYYY-MM-DD)")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio,

            @Parameter(description = "Fecha de fin (YYYY-MM-DD)")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fin,

            @Parameter(description = "Token proporcionado por el usuario", required = true)
            @RequestParam("token") String token) {

        ContenedorHistorialDTO historial = ContenedorService.consultarContenedor(id, inicio, fin);

        if (historial == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(historial);
    }
}

