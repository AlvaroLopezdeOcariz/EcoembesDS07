package com.dto;

import java.util.Date;

public class CapacidadPlantaDTO {
	
	private String plantaId;
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
    public String getPlantaId() { return plantaId; }
    public void setPlantaId(String plantaId) { this.plantaId = plantaId; }
    public void setNombrePlanta(String nombrePlanta) { this.nombrePlanta = nombrePlanta; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setCapacidadDisponible(Integer capacidadDisponible) { this.capacidadDisponible = capacidadDisponible; }
}
