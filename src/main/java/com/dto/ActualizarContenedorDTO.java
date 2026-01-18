package com.dto;

public class ActualizarContenedorDTO {
    
    private Integer nivelLlenado;
    private Integer numEnvases;
    
    public ActualizarContenedorDTO() {}
    
    public Integer getNivelLlenado() { return nivelLlenado; }
    public void setNivelLlenado(Integer nivelLlenado) { this.nivelLlenado = nivelLlenado; }
    
    public Integer getNumEnvases() { return numEnvases; }
    public void setNumEnvases(Integer numEnvases) { this.numEnvases = numEnvases; }
}