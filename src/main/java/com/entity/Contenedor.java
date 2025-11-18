package com.entity;

import java.util.ArrayList;
import java.util.List;

public class Contenedor {

    private String id;
    private String ubicacion;
    private int capacidad;
    private int nivelLlenado;
    private int numEnvases;
    private int codigoPostal;

    private List<RegistroUsoContenedor> historial = new ArrayList<>();

    public Contenedor() {}

    public Contenedor(String id, String ubicacion, int capacidad, int codigoPostal) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.codigoPostal = codigoPostal;
        this.nivelLlenado = 0;
        this.numEnvases = 0;
    }

    public void actualizarEstado() {
        // vacio según UML
    }

    public int consultarCapacidad() {
        return capacidad - nivelLlenado;
    }

    public String getId() { return id; }
    public String getUbicacion() { return ubicacion; }
    public int getCapacidad() { return capacidad; }
    public int getNivelLlenado() { return nivelLlenado; }
    public int getNumEnvases() { return numEnvases; }
    public int getCodigoPostal() { return codigoPostal; }
    public List<RegistroUsoContenedor> getHistorial() { return historial; }

    public void setNivelLlenado(int nivelLlenado) { this.nivelLlenado = nivelLlenado; }
    public void setNumEnvases(int numEnvases) { this.numEnvases = numEnvases; }
}
