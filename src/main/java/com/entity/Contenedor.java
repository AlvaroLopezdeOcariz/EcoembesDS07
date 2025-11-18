package com.entity;

import java.util.ArrayList;
import java.util.List;

public class Contenedor {

    private String id;
    private String ubicacion;
    private Integer capacidad;
    private Integer nivelLlenado;
    private Integer numEnvases;
    private Integer codigoPostal;
    private List<RegistroUsoContenedor> historial = new ArrayList<>();

    public Contenedor() {}

    public Contenedor(String id, String ubicacion, Integer capacidad, Integer codigoPostal) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.codigoPostal = codigoPostal;
        this.nivelLlenado = 0;
        this.numEnvases = 0;
    }

    public void actualizarEstado() {}

    public int consultarCapacidad() {
        return capacidad - nivelLlenado;
    }

    public String getId() { return id; }
    public String getUbicacion() { return ubicacion; }
    public Integer getCapacidad() { return capacidad; }
    public Integer getNivelLlenado() { return nivelLlenado; }
    public Integer getNumEnvases() { return numEnvases; }
    public Integer getCodigoPostal() { return codigoPostal; }
    public List<RegistroUsoContenedor> getHistorial() { return historial; }

    public void setNivelLlenado(Integer nivelLlenado) { this.nivelLlenado = nivelLlenado; }
    public void setNumEnvases(Integer numEnvases) { this.numEnvases = numEnvases; }
    public void setCodigoPostal(Integer codigoPostal) { this.codigoPostal = codigoPostal; }
}

