package com.AppServices;

import java.util.*;
import org.springframework.stereotype.Service;

import com.dto.CapacidadPlantaDTO;
import com.entity.PlantaReciclaje;

import dao.PlantaRepository;
import external.ServiceGateway;
import factory.ServiceGatewayFactory;

@Service
public class PlantaService {

   
    private PlantaRepository plantaRepo;
    
    public PlantaReciclaje getPlanta(String idPlanta) {
		return plantaRepo.findById(idPlanta).orElse(null);
    	
    }

    
    	
    	
      
    

    public CapacidadPlantaDTO consultarCapacidad(String idPlanta, Date fecha) {

    	PlantaReciclaje plantaReciclaje = getPlanta(idPlanta);
    	
    	 ServiceGateway gateway =ServiceGatewayFactory.create(plantaReciclaje.getTipoServidor(),plantaReciclaje.getUrlBase(),plantaReciclaje.getPuerto());

    	       
    	        CapacidadPlantaDTO capacidad = gateway.consultarCapacidad(idPlanta);

    	        if (capacidad == null) {
    	            return null; 
    	        }

    	      
    	        capacidad.setFecha(fecha);

    	        return capacidad;
    	    }



}
