package com.ecoembes.p1.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.ActualizarContenedorDTO;
import com.dto.ContenedorHistorialDTO;
import com.dto.CrearContenedorDTO;
import com.dto.ContenedorZonaDTO;
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
            @PathVariable("id") String id,
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fin,
            @RequestParam("token") String token) {

        ContenedorHistorialDTO historial = contenedorService.consultarContenedor(id, inicio, fin);

        if (historial == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(historial);
    }
    
    @Operation(
    	    summary = "Consultar contenedores por código postal",
    	    description = "Obtiene todos los contenedores de una zona específica en una fecha determinada"
    	)
    	@ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    	@GetMapping("/zona/{codigoPostal}")
    	public ResponseEntity<List<ContenedorZonaDTO>> consultarPorZona(
    	        @PathVariable("codigoPostal") String codigoPostal,
    	        @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha,
    	        @RequestParam("token") String token) {
    	    
    	    List<ContenedorZonaDTO> contenedores = contenedorService.buscarPorZona(codigoPostal, fecha);
    	    
    	    if (contenedores == null || contenedores.isEmpty()) {
    	        return ResponseEntity.notFound().build();
    	    }
    	    
    	    return ResponseEntity.ok(contenedores);
    	}
    
    @Operation(
    	    summary = "Actualizar información de un contenedor",
    	    description = "Actualiza el nivel de llenado y número de envases (simula sensor)"
    	)
    	@ApiResponse(responseCode = "200", description = "Contenedor actualizado")
    	@PutMapping("/{id}")
    	public ResponseEntity<Contenedor> actualizar(
    	        @PathVariable("id") String id,
    	        @RequestBody ActualizarContenedorDTO dto,
    	        @RequestParam("token") String token) {
    	    
    	    Contenedor actualizado = contenedorService.actualizarContenedor(id, dto);
    	    return ResponseEntity.ok(actualizado);
    	}
}

