package com.entity;

import java.util.Date;
import java.util.List;

public class AsignacionPlanta {

    private String id;
    private Date fecha;
    private PlantaReciclaje planta;
    private List<Contenedor> contenedores;
    private Integer totalEnvases;
    private Empleado usuarioAsignador;

    public AsignacionPlanta() {}

    public AsignacionPlanta(String id, Date fecha, PlantaReciclaje planta,
                            List<Contenedor> contenedores, Empleado usuarioAsignador) {
        this.id = id;
        this.fecha = fecha;
        this.planta = planta;
        this.contenedores = contenedores;
        this.usuarioAsignador = usuarioAsignador;
    }

    // getters/setters
    public String getId() { return id; }
    public Date getFecha() { return fecha; }
    public PlantaReciclaje getPlanta() { return planta; }
    public List<Contenedor> getContenedores() { return contenedores; }
    public Integer getTotalEnvases() { return totalEnvases; }
    public Empleado getUsuarioAsignador() { return usuarioAsignador; }

    public void setTotalEnvases(Integer totalEnvases) { this.totalEnvases = totalEnvases; }
}
