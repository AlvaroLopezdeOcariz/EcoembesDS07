package com.AppServices;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.entity.Empleado;

@Service
public class AuthService {

    // Token fijo para pruebas
    public static final String TOKEN_TEST = "TOKEN_FIJO";

    // Usuarios (simulación de BD)
    private final Map<String, Empleado> empleados = new HashMap<>();

    // Tokens activos → token : empleado
    private final Map<String, Empleado> tokensActivos = new HashMap<>();

    public AuthService() {
        Empleado admin = new Empleado("1", "Admin", "admin@ecoembes.com", "1234");

        // Usuario registrado
        empleados.put(admin.getEmail(), admin);

        // Token fijo de pruebas
        tokensActivos.put(TOKEN_TEST, admin);
    }

    /**
     * Login: valida credenciales y genera token
     */
    public String login(String email, String password) {
        Empleado emp = empleados.get(email);

        if (emp == null) return null;
        if (!emp.getPassword().equals(password)) return null;

        // Generar token "real"
        String token = UUID.randomUUID().toString();
        tokensActivos.put(token, emp);

        return token;
    }

    /**
     * Logout: invalida el token
     */
    public void logout(String token) {
        if (!TOKEN_TEST.equals(token)) {
            tokensActivos.remove(token);
        }
    }

    /**
     * Comprueba si el token es válido (test o real)
     */
    public boolean esTokenValido(String token) {
        return token != null && tokensActivos.containsKey(token);
    }

    /**
     * Devuelve el usuario asociado al token
     */
    public Empleado obtenerUsuarioDesdeToken(String token) {
        return tokensActivos.get(token);
    }
}


