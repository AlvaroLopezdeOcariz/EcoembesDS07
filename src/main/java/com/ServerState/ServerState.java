package com.ServerState;

import com.entity.Empleado;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServerState {
    
    // Mapa de tokens activos: token -> empleado
    private final Map<String, Empleado> tokensActivos = new HashMap<>();
    
    /**
     * Añade un token al estado del servidor
     */
    public void addToken(String token, Empleado empleado) {
        tokensActivos.put(token, empleado);
        System.out.println("[ServerState] Token añadido: " + token + " para " + empleado.getEmail());
    }
    
    /**
     * Elimina un token del estado
     */
    public void removeToken(String token) {
        Empleado removed = tokensActivos.remove(token);
        if (removed != null) {
            System.out.println("[ServerState] Token eliminado: " + token);
        }
    }
    
    /**
     * Verifica si un token existe
     */
    public boolean existeToken(String token) {
        return tokensActivos.containsKey(token);
    }
    
    /**
     * Obtiene el empleado asociado a un token
     */
    public Empleado getEmpleadoPorToken(String token) {
        return tokensActivos.get(token);
    }
    
    /**
     * Obtiene todos los tokens activos (útil para debugging)
     */
    public Map<String, Empleado> getTokensActivos() {
        return new HashMap<>(tokensActivos);
    }
}
