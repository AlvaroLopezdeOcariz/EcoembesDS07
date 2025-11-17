package com.ecoembes.p1.domain;

import java.time.LocalDate;
import java.util.List;

public class AsignacionPlanta {

    private String id;
    private LocalDate fecha;
    private PlantaReciclaje planta;
    private List<Contenedor> contenedores;
    private int totalEnvases;
    private Empleado usuarioAsignador;

    public AsignacionPlanta() {}

    public AsignacionPlanta(String id, LocalDate fecha, PlantaReciclaje planta,
                            List<Contenedor> contenedores, Empleado usuarioAsignador) {
        this.id = id;
        this.fecha = fecha;
        this.planta = planta;
        this.contenedores = contenedores;
        this.usuarioAsignador = usuarioAsignador;
    }

    // getters/setters
    public String getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public PlantaReciclaje getPlanta() { return planta; }
    public List<Contenedor> getContenedores() { return contenedores; }
    public int getTotalEnvases() { return totalEnvases; }
    public Empleado getUsuarioAsignador() { return usuarioAsignador; }

    public void setTotalEnvases(int totalEnvases) { this.totalEnvases = totalEnvases; }
}
