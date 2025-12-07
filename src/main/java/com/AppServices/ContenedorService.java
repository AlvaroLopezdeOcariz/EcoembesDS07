package com.AppServices;

import java.util.*;
import org.springframework.stereotype.Service;

import com.dto.ContenedorHistorialDTO;
import com.dto.CrearContenedorDTO;
import com.dto.RegistroUsoDTO;
import com.entity.Contenedor;
import com.entity.RegistroUsoContenedor;

@Service
public class ContenedorService {

    private final static Map<String, Contenedor> contenedores = new HashMap<>();

    public ContenedorService() {
        contenedores.put("0001", new Contenedor("0001", "vitoria", 111, 1111));
        contenedores.put("0002", new Contenedor("0002", "bilbao", 120, 2222));
        contenedores.put("0003", new Contenedor("0003", "vitoria", 5, 1122));
    }

    public Contenedor crearContenedor(CrearContenedorDTO dto) {

        if (dto.getCodigoPostal() == null || dto.getCodigoPostal().isEmpty()) {
            throw new IllegalArgumentException("codigoPostal no puede ser null o vacío");
        }

        int cp = Integer.parseInt(dto.getCodigoPostal());

        Contenedor c = new Contenedor(
                dto.getId(),
                dto.getUbicacion(),
                dto.getCapacidad(),
                cp
        );

        contenedores.put(c.getId(), c);
        return c;
    }

    public static ContenedorHistorialDTO consultarContenedor(String id, Date inicio, Date fin) {

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

    public void actualizarSensor(String idContenedor, String nivel) {

        Contenedor c = contenedores.get(idContenedor);
        if (c != null) {
            c.setNivelLlenado(Integer.parseInt(nivel));
        }
    }

    private static List<RegistroUsoDTO> convertirHistorial(List<RegistroUsoContenedor> lista) {

        List<RegistroUsoDTO> out = new ArrayList<>();
        for (RegistroUsoContenedor r : lista) {
            out.add(new RegistroUsoDTO(
                    r.getId(),
                    r.getFecha(),
                    r.getNivelLlenado()
            ));
        }
        return out;
    }

    // útil para AsignacionService
    public Contenedor getContenedor(String id) {
        return contenedores.get(id);
    }
}
