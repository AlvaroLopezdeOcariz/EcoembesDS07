package com.AppServices;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.entity.Empleado;

@Service
public class AuthService {

    private final Map<String, Empleado> empleados = new HashMap<>();

    public AuthService() {
        empleados.put("admin@ecoembes.com",
                new Empleado("1", "Admin", "admin@ecoembes.com", "1234"));
    }

    public Empleado login(String email, String password) {
        Empleado emp = empleados.get(email);
        if (emp == null) return null;
        if (!emp.getPassword().equals(password)) return null;
        return emp;
    }

    public void logout(String token) {
        // De momento vacío (simulado)
    }
}
