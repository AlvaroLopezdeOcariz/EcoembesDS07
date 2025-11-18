package com.facade;

import org.springframework.stereotype.Service;

import com.AppServices.AppServices;
import com.ServerState.ServerState;
import com.dto.AsignacionPlantaDTO;
import com.dto.AsignacionResultadoDTO;
import com.dto.CapacidadPlantaDTO;
import com.dto.ContenedorHistorialDTO;
import com.dto.CrearContenedorDTO;
import com.dto.LoginDTO;
import com.entity.AsignacionPlanta;
import com.entity.Contenedor;
import com.entity.Empleado;

import java.util.*;


@Service
public class Facade {

    private final AppServices app;
    private final ServerState state;

    public Facade(AppServices app, ServerState state) {
        this.app = app;
        this.state = state;
    }

    
    public LoginDTO login(String email, String pass) {
        Empleado emp = app.login(email, pass);
        if (emp == null) return null;

        String token = UUID.randomUUID().toString();
        state.anadirSesion(token, emp);

        return new LoginDTO(token, emp.getId(), emp.getEmail());
    }

    public void logout(String token) {
        state.borrarSesion(token);
    }

    
    public Contenedor crearContenedor(CrearContenedorDTO dto, String token) {
        validar(token);

        return app.crearContenedor(dto);
    }

    public ContenedorHistorialDTO consultarContenedor(String id,
                                                      Date ini,
                                                      Date fin,
                                                      String token) {
        validar(token);

        return app.consultarContenedor(id, ini, fin);
    }

    
    public void actualizarSensor(String idContenedor, String nivel) {
        app.actualizarSensor(idContenedor, nivel);
    }

   
    public CapacidadPlantaDTO consultarCapacidadPlantaDia(Integer idPlanta,
                                                          Date fecha,
                                                          String token) {
        validar(token);
        return app.consultarCapacidad(idPlanta, fecha);
    }

    
    public AsignacionResultadoDTO asignarContenedoresAPlanta(
            AsignacionPlantaDTO dto,
            String token) {

        Empleado emp = validar(token);

        AsignacionPlanta asign = app.asignarContenedoresAPlanta(
                dto.getNombre(),
                dto.getListaContenedores(),
                emp);

        return new AsignacionResultadoDTO(
                asign.getId(),
                asign.getPlanta().getNombre(),
                asign.getFecha(),
                asign.getContenedores().size(),
                asign.getTotalEnvases()
        );
    }

   
    private Empleado validar(String token) {
        if (!state.tokenValido(token)) {
            throw new RuntimeException("Token inválido");
        }
        return state.getEmpleado(token);
    }
}
