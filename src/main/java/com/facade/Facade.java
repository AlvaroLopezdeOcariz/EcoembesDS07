package com.facade;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.AppServices.AsignacionService;
import com.AppServices.AuthService;
import com.AppServices.ContenedorService;
import com.AppServices.PlantaService;
import com.dto.*;
import com.entity.Contenedor;
import com.entity.Empleado;


@Service
public class Facade {

    private final AuthService authService;
    private final ContenedorService contenedorService;
    private final PlantaService plantaService;
    private final AsignacionService asignacionService;

    public Facade(AuthService authService,
                  ContenedorService contenedorService,
                  PlantaService plantaService,
                  AsignacionService asignacionService) {

        this.authService = authService;
        this.contenedorService = contenedorService;
        this.plantaService = plantaService;
        this.asignacionService = asignacionService;
    }

    // =========================
    // AUTH
    // =========================

    public LoginDTO login(String email, String password) {

        Empleado emp = authService.login(email, password);
        if (emp == null) return null;

        // token simulado
        String token = UUID.randomUUID().toString();


        return new LoginDTO(token, emp.getId(), emp.getEmail());
    }

    public void logout(String token) {
        authService.logout(token);
    }

    // =========================
    // CONTENEDORES
    // =========================

    public Contenedor crearContenedor(CrearContenedorDTO dto, String token) {
        validarToken(token);
        return contenedorService.crearContenedor(dto);
    }

    public ContenedorHistorialDTO consultarContenedor(String id,
                                                      Date inicio,
                                                      Date fin,
                                                      String token) {
        validarToken(token);
        return contenedorService.consultarContenedor(id, inicio, fin);
    }

    // =========================
    // PLANTAS
    // =========================

    public CapacidadPlantaDTO consultarCapacidadPlantaDia(Integer idPlanta,
                                                           Date fecha,
                                                           String token) {
        validarToken(token);
        return plantaService.consultarCapacidad(idPlanta, fecha);
    }

    // =========================
    // ASIGNACIONES
    // =========================

    public AsignacionResultadoDTO asignarContenedoresAPlanta(AsignacionPlantaDTO dto,
                                                             String token) {

        validarToken(token);

        Empleado usuario = obtenerUsuarioDesdeToken(token);

        return asignacionService.asignarContenedoresAPlanta(
                dto.getNombre(),
                dto.getListaContenedores(),
                usuario
        );
    }

    // =========================
    // MÉTODOS AUXILIARES
    // =========================

    private void validarToken(String token) {
    	  System.out.println("TOKEN RECIBIDO: [" + token + "]");
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token inválido");
        }
    }

    private Empleado obtenerUsuarioDesdeToken(String token) {

        return new Empleado("1", "Admin", "admin@ecoembes.com", "1234");
    }
}


