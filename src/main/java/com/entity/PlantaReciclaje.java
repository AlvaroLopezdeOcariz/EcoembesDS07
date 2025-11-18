package com.entity;

public class PlantaReciclaje {

    private int id;
    private String nombre;
    private String ubicacion;
    private int maximaCapacidad;
    private int llenadoActual;
    private int codigoPostal;
    private int totalContenedores;

    public PlantaReciclaje() {}

    public PlantaReciclaje(int id, String nombre, String ubicacion,
                            int maximaCapacidad, int codigoPostal) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.maximaCapacidad = maximaCapacidad;
        this.codigoPostal = codigoPostal;
        this.llenadoActual = 0;
        this.totalContenedores = 0;
    }

    public int ConsultarCapacidad() {
        return maximaCapacidad - llenadoActual;
    }

    public void RecibirContenedor() {
        totalContenedores++;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public int getMaximaCapacidad() { return maximaCapacidad; }
    public int getLlenadoActual() { return llenadoActual; }
    public int getCodigoPostal() { return codigoPostal; }
    public int getTotalContenedores() { return totalContenedores; }

    public void setLlenadoActual(int llenadoActual) { this.llenadoActual = llenadoActual; }
}
