package com.AppServices;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.dto.CapacidadPlantaDTO;
import com.entity.PlantaReciclaje;

import dao.PlantaRepository;
import external.ServiceGateway;
import factory.ServiceGatewayFactory;

@Service
public class PlantaService {

    private final PlantaRepository plantaRepo;

    public PlantaService(PlantaRepository plantaRepo) {
        this.plantaRepo = plantaRepo;
    }

    public PlantaReciclaje getPlanta(String idPlanta) {
        return plantaRepo.findById(idPlanta)
                .orElseThrow(() -> new RuntimeException("Planta no encontrada: " + idPlanta));
    }

    public CapacidadPlantaDTO consultarCapacidad(String idPlanta, String fecha) {

        LocalDate fechaConsulta = LocalDate.parse(fecha);

        PlantaReciclaje planta = getPlanta(idPlanta);

        ServiceGateway gateway = ServiceGatewayFactory.create(
                planta.getTipoServidor(),
                planta.getUrlBase(),
                planta.getPuerto()
        );

        // ✅ LLAMADA CORRECTA
        CapacidadPlantaDTO capacidad = gateway.consultarCapacidad(fechaConsulta);

        if (capacidad == null) {
            throw new RuntimeException("No se pudo obtener la capacidad de la planta");
        }

        capacidad.setFecha(fechaConsulta);

        return capacidad;
    }
}
