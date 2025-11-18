package com.ServerState;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.entity.Empleado;

@Component
public class ServerState {

    private final Map<String, Empleado> sesionesActivas = new HashMap<>();

    public void anadirSesion(String token, Empleado empleado) {
        sesionesActivas.put(token, empleado);
    }

    public void borrarSesion(String token) {
        sesionesActivas.remove(token);
    }

    public boolean tokenValido(String token) {
        return sesionesActivas.containsKey(token);
    }

    public Empleado getEmpleado(String token) {
        return sesionesActivas.get(token);
    }
}
