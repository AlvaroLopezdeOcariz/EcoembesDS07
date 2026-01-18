package com.dto;

import java.util.Date;

public class RegistroUsoDTO {
    
    private Date fecha;
    private Integer nivelLlenado;
    private Integer numEnvases;
    
    public RegistroUsoDTO() {}
    
    public RegistroUsoDTO(Date fecha, Integer nivelLlenado, Integer numEnvases) {
        this.fecha = fecha;
        this.nivelLlenado = nivelLlenado;
        this.numEnvases = numEnvases;
    }
    
    // Getters y Setters
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Integer getNivelLlenado() {
        return nivelLlenado;
    }
    
    public void setNivelLlenado(Integer nivelLlenado) {
        this.nivelLlenado = nivelLlenado;
    }
    
    public Integer getNumEnvases() {
        return numEnvases;
    }
    
    public void setNumEnvases(Integer numEnvases) {
        this.numEnvases = numEnvases;
    }
}