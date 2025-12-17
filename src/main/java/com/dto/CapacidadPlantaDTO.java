package com.dto;

import java.time.LocalDate;

public class CapacidadPlantaDTO {

    private String plantaId;
    private String nombrePlanta;
    private LocalDate fecha;
    private Integer capacidadDisponible;

    public CapacidadPlantaDTO() {}

    public CapacidadPlantaDTO(String nombrePlanta, LocalDate fecha, Integer capacidadDisponible) {
        this.nombrePlanta = nombrePlanta;
        this.fecha = fecha;
        this.capacidadDisponible = capacidadDisponible;
    }

    public String getPlantaId() {
        return plantaId;
    }

    public void setPlantaId(String plantaId) {
        this.plantaId = plantaId;
    }

    public String getNombrePlanta() {
        return nombrePlanta;
    }

    public void setNombrePlanta(String nombrePlanta) {
        this.nombrePlanta = nombrePlanta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getCapacidadDisponible() {
        return capacidadDisponible;
    }

    public void setCapacidadDisponible(Integer capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }
}
