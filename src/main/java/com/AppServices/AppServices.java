package com.AppServices;

import java.util.*;
import org.springframework.stereotype.Service;

import com.dto.CapacidadPlantaDTO;
import com.dto.ContenedorHistorialDTO;
import com.dto.CrearContenedorDTO;
import com.dto.RegistroUsoDTO;
import com.entity.AsignacionPlanta;
import com.entity.Contenedor;
import com.entity.Empleado;
import com.entity.PlantaReciclaje;
import com.entity.RegistroUsoContenedor;



@Service
public class AppServices {

    
    private final Map<String, Empleado> empleados = new HashMap<>();
    private final Map<String, Contenedor> contenedores = new HashMap<>();
    private final Map<Integer, PlantaReciclaje> plantas = new HashMap<>();
    private final List<AsignacionPlanta> asignaciones = new ArrayList<>();

    public AppServices() {
        
        empleados.put("admin@ecoembes.com", new Empleado("1", "Admin", "admin@ecoembes.com", "1234"));
    }

    
    public Empleado login(String email, String password) {
        Empleado emp = empleados.get(email);
        if (emp == null) return null;
        if (!emp.getPassword().equals(password)) return null;
        return emp;
    }

    public void logout(String token) {
       
    }

    
    public Contenedor crearContenedor(CrearContenedorDTO dto) {
        Contenedor c = new Contenedor(
            dto.getId(),
            dto.getUbicacion(),
            dto.getCapacidad(),
            Integer.parseInt(dto.getCodigPostal())
        );

        contenedores.put(c.getId(), c);
        return c;
    }

    public ContenedorHistorialDTO consultarContenedor(String id, Date inicio, Date fin) {
        Contenedor c = contenedores.get(id);
        if (c == null) return null;

        return new ContenedorHistorialDTO(
            c.getId(),
            c.getUbicacion(),
            String.valueOf(c.getCodigoPostal()),
            c.getCapacidad(),
            inicio,
            fin,
            convertirHistorial(c.getHistorial())
        );
    }

    private List<RegistroUsoDTO> convertirHistorial(List<RegistroUsoContenedor> lista) {
        List<RegistroUsoDTO> out = new ArrayList<>();
        for (RegistroUsoContenedor r : lista) {
            out.add(new RegistroUsoDTO(r.getId(), r.getFecha(), r.getNivelLlenado()));
        }
        return out;
    }

    public void actualizarSensor(String idContenedor, String nivel) {
        Contenedor c = contenedores.get(idContenedor);
        if (c != null) {
            c.setNivelLlenado(Integer.parseInt(nivel));
        }
    }

    
    public CapacidadPlantaDTO consultarCapacidad(int idPlanta, Date fecha) {
        PlantaReciclaje p = plantas.get(idPlanta);
        if (p == null) return null;

        return new CapacidadPlantaDTO(
            p.getNombre(),
            fecha,
            p.ConsultarCapacidad()
        );
    }

   
       public AsignacionPlanta asignarContenedoresAPlanta(String idPlant,
                                                       List<Contenedor> lista,
                                                       Empleado usuario) {
        PlantaReciclaje planta = plantas.get(Integer.parseInt(idPlant));
        if (planta == null) return null;

        AsignacionPlanta asignacion = new AsignacionPlanta(
            UUID.randomUUID().toString(),
            new Date(),
            planta,
            lista,
            usuario
        );

        asignaciones.add(asignacion);
        return asignacion;
    }
}
