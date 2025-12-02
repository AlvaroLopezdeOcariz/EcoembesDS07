package com.AppServices;

import java.util.*;
import org.springframework.stereotype.Service;

import com.dto.AsignacionResultadoDTO;
import com.entity.AsignacionPlanta;
import com.entity.Contenedor;
import com.entity.Empleado;
import com.entity.PlantaReciclaje;

@Service
public class AsignacionService {

    private final List<AsignacionPlanta> asignaciones = new ArrayList<>();
    private final PlantaService plantaService;

    public AsignacionService(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    public AsignacionResultadoDTO asignarContenedoresAPlanta(String idPlant,
                                                             List<Contenedor> lista,
                                                             Empleado usuario) {

        PlantaReciclaje planta = plantaService.getPlanta(Integer.parseInt(idPlant));
        if (planta == null) return null;

        AsignacionPlanta asignacion = new AsignacionPlanta(
                UUID.randomUUID().toString(),
                new Date(),
                planta,
                lista,
                usuario
        );

        asignaciones.add(asignacion);

        return new AsignacionResultadoDTO(
                asignacion.getId(),
                planta.getNombre(),
                asignacion.getFecha(),
                lista.size(),
                calcularEnvases(lista)
        );
    }

    private int calcularEnvases(List<Contenedor> lista) {
        int total = 0;
        for (Contenedor c : lista) {
            total += c.getNivelLlenado();
        }
        return total;
    }
}
