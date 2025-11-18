package com.entity;

public class PlantaReciclaje {

    private Integer id;
    private String nombre;
    private String ubicacion;
    private Integer maximaCapacidad;
    private Integer llenadoActual;
    private Integer codigoPostal;
    private Integer totalContenedores;

    public PlantaReciclaje() {}

    public PlantaReciclaje(Integer id, String nombre, String ubicacion,
    		Integer maximaCapacidad, Integer codigoPostal) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.maximaCapacidad = maximaCapacidad;
        this.codigoPostal = codigoPostal;
        this.llenadoActual = 0;
        this.totalContenedores = 0;
    }

    public Integer ConsultarCapacidad() {
        return maximaCapacidad - llenadoActual;
    }

    public void RecibirContenedor() {
        totalContenedores++;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public Integer getMaximaCapacidad() { return maximaCapacidad; }
    public Integer getLlenadoActual() { return llenadoActual; }
    public Integer getCodigoPostal() { return codigoPostal; }
    public Integer getTotalContenedores() { return totalContenedores; }

    public void setLlenadoActual(Integer llenadoActual) { this.llenadoActual = llenadoActual; }
}
