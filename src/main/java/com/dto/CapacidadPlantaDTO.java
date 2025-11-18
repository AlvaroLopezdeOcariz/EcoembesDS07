package com.dto;

import java.util.Date;

public class CapacidadPlantaDTO {

    private String nombrePlanta;
    private Date fecha;
    private Integer capacidadDisponible;

    public CapacidadPlantaDTO() {}
    public CapacidadPlantaDTO(String nombrePlanta, Date fecha, Integer capacidadDisponible) {
		this.nombrePlanta = nombrePlanta;
		this.fecha = fecha;
		this.capacidadDisponible = capacidadDisponible;
	}
    public String getNombrePlanta() { return nombrePlanta; }
    public Date getFecha() { return fecha; }
    public int getCapacidadDisponible() { return capacidadDisponible; }
}
