package com.AppServices;

import java.util.*;
import org.springframework.stereotype.Service;

import com.dto.CapacidadPlantaDTO;
import com.entity.PlantaReciclaje;

@Service
public class PlantaService {

    private final Map<Integer, PlantaReciclaje> plantas = new HashMap<>();

    public PlantaService() {
        plantas.put(1, new PlantaReciclaje(1, "Planta1", "donosti", 10000, 30303));
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

    public PlantaReciclaje getPlanta(int idPlanta) {
        return plantas.get(idPlanta);
    }
}
