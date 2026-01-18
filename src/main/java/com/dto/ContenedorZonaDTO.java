package com.dto;

import java.util.Date;

public class ContenedorZonaDTO {
    
    private String id;
    private String ubicacion;
    private Integer capacidad;
    private Integer nivelLlenado;
    private Integer numEnvases;
    private String estado; // "VERDE", "NARANJA", "ROJO"
    private Date fechaConsulta;
    
    public ContenedorZonaDTO() {}
    
    public ContenedorZonaDTO(String id, String ubicacion, Integer capacidad, 
                             Integer nivelLlenado, Integer numEnvases, 
                             String estado, Date fechaConsulta) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.nivelLlenado = nivelLlenado;
        this.numEnvases = numEnvases;
        this.estado = estado;
        this.fechaConsulta = fechaConsulta;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    
    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    
    public Integer getNivelLlenado() { return nivelLlenado; }
    public void setNivelLlenado(Integer nivelLlenado) { this.nivelLlenado = nivelLlenado; }
    
    public Integer getNumEnvases() { return numEnvases; }
    public void setNumEnvases(Integer numEnvases) { this.numEnvases = numEnvases; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public Date getFechaConsulta() { return fechaConsulta; }
    public void setFechaConsulta(Date fechaConsulta) { this.fechaConsulta = fechaConsulta; }
}