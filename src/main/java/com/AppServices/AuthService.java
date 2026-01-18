package com.AppServices;

import com.entity.Empleado;
import com.ServerState.ServerState;
import dao.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final EmpleadoRepository empleadoRepository;
    private final ServerState serverState;

    // Constructor con inyección de dependencias
    public AuthService(EmpleadoRepository empleadoRepository, ServerState serverState) {
        this.empleadoRepository = empleadoRepository;
        this.serverState = serverState;
    }

    public String login(String email, String password) {
        
        System.out.println("======= DEBUG LOGIN =======");
        System.out.println("Email recibido: '" + email + "'");
        System.out.println("Password recibido: '" + password + "'");
        
        // Buscar empleado por email en la base de datos
        Optional<Empleado> empleadoOpt = empleadoRepository.findByEmail(email);
        
        if (empleadoOpt.isEmpty()) {
            System.err.println("ERROR: Usuario no encontrado en BD");
            throw new RuntimeException("Usuario no encontrado");
        }
        
        Empleado empleado = empleadoOpt.get();
        System.out.println("Usuario encontrado: " + empleado.getEmail());
        System.out.println("Password en BD: '" + empleado.getPassword() + "'");
        
        // Verificar contraseña
        if (!empleado.getPassword().equals(password)) {
            System.err.println("ERROR: Password NO coincide");
            System.err.println("  Esperado: '" + empleado.getPassword() + "'");
            System.err.println("  Recibido: '" + password + "'");
            throw new RuntimeException("Credenciales incorrectas");
        }
        
        System.out.println("✓ Password correcto");
        
        // Generar token (timestamp)
        String token = String.valueOf(System.currentTimeMillis());
        
        // Guardar token en el estado del servidor
        serverState.addToken(token, empleado);
        
        System.out.println("✓ Token generado: " + token);
        System.out.println("===========================");
        
        return token;
    }

    public void logout(String token) {
        if (token != null && !token.isEmpty()) {
            serverState.removeToken(token);
            System.out.println("[AuthService] Logout exitoso: " + token);
        }
    }

    public boolean esTokenValido(String token) {
        boolean valido = serverState.existeToken(token);
        System.out.println("[AuthService] Token " + token + " es válido: " + valido);
        return valido;
    }

    public Empleado obtenerUsuarioDesdeToken(String token) {
        Empleado emp = serverState.getEmpleadoPorToken(token);
        if (emp == null) {
            System.err.println("[AuthService] No se encontró empleado para token: " + token);
        }
        return emp;
    }
}