package com.dto;

public class CrearContenedorDTO {

    private String id;
    private String ubicacion;
    private String codigoPostal; 
    private Integer capacidad;

    public CrearContenedorDTO() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
}
