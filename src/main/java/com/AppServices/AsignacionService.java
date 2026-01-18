package com.AppServices;

import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;

import com.dto.AsignacionResultadoDTO;
import com.dto.CapacidadPlantaDTO;
import com.entity.AsignacionPlanta;
import com.entity.Contenedor;
import com.entity.Empleado;
import com.entity.PlantaReciclaje;

import external.ServiceGateway;
import factory.ServiceGatewayFactory;

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

	// 1. Obtener la planta
	PlantaReciclaje planta = plantaService.getPlanta(idPlant);
	if (planta == null) {
		throw new RuntimeException("Planta no encontrada: " + idPlant);
	}
	
	// 2. Consultar capacidad disponible HOY
	LocalDate hoy = LocalDate.now();
	CapacidadPlantaDTO capacidad = plantaService.consultarCapacidad(idPlant, hoy.toString());
	
	// 3. Calcular cuántos kilos necesitamos
	double kgNecesarios = calcularKilosDeContenedores(lista);
	
	// 4. VERIFICAR si hay capacidad suficiente
	if (capacidad.getCapacidadDisponible() < kgNecesarios) {
	throw new RuntimeException(
	String.format("Capacidad insuficiente. Disponible: %.2f kg, Necesario: %.2f kg", 
	capacidad.getCapacidadDisponible(), kgNecesarios));
	}
	
	// 5. Crear la asignación
	AsignacionPlanta asignacion = new AsignacionPlanta(
	UUID.randomUUID().toString(),
	new Date(),
	planta,
	lista,
	usuario
	);
	
	asignaciones.add(asignacion);
	
	// 6. NOTIFICAR A LA PLANTA
	try {
	ServiceGateway gateway = ServiceGatewayFactory.create(
	planta.getTipoServidor(),
	planta.getUrlBase(),
	planta.getPuerto()
	);
	
	gateway.notificarAsignacion(lista.size(), (int) kgNecesarios, hoy);
	
	System.out.println("[AsignacionService] Notificación enviada a planta: " + planta.getNombre());
	
	} catch (Exception e) {
	System.err.println("[AsignacionService] Error al notificar planta: " + e.getMessage());
	// No lanzamos excepción para no romper la asignación
	}
	
	// 7. Retornar resultado
	return new AsignacionResultadoDTO(
	asignacion.getId(),
	planta.getNombre(),
	asignacion.getFecha(),
	lista.size(),
	(int) kgNecesarios
	);
	}
	
    private double calcularKilosDeContenedores(List<Contenedor> contenedores) {
        double totalKg = 0;
        
        for (Contenedor c : contenedores) {
            // Suponemos que cada envase pesa 0.05 kg (50 gramos)
            // Esto es una estimación, podéis ajustarla
            Integer numEnvases = c.getNumEnvases();
            if (numEnvases != null) {  // ✅ VALIDACIÓN AÑADIDA
                totalKg += numEnvases * 0.05;
            }
        }
        
        return totalKg;
    }

    private int calcularEnvases(List<Contenedor> lista) {
        int total = 0;
        for (Contenedor c : lista) {
            total += c.getNivelLlenado();
        }
        return total;
    }
}
