package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;  
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contenedor")
public class Contenedor {
    
    @Id
    @Column(name = "id", length = 50)
    private String id;
    
    @Column(name = "ubicacion", length = 200)
    private String ubicacion;
    
    @Column(name = "capacidad")
    private Integer capacidad;
    
    @Column(name = "codigo_postal")
    private Integer codigoPostal;
    
    @Column(name = "nivel_llenado")
    private Integer nivelLlenado;
    
    @Column(name = "num_envases")
    private Integer numEnvases;
    
    @OneToMany(mappedBy = "contenedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // ✅ AÑADIDO - No serializar en JSON
    private List<RegistroUsoContenedor> historial = new ArrayList<>();
    
    // Constructores
    public Contenedor() {}
    
    public Contenedor(String id, String ubicacion, Integer capacidad, Integer codigoPostal) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.codigoPostal = codigoPostal;
        this.nivelLlenado = 0;
        this.numEnvases = 0;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    
    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    
    public Integer getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(Integer codigoPostal) { this.codigoPostal = codigoPostal; }
    
    public Integer getNivelLlenado() { return nivelLlenado; }
    public void setNivelLlenado(Integer nivelLlenado) { this.nivelLlenado = nivelLlenado; }
    
    public Integer getNumEnvases() { return numEnvases; }
    public void setNumEnvases(Integer numEnvases) { this.numEnvases = numEnvases; }
    
    public List<RegistroUsoContenedor> getHistorial() { return historial; }
    public void setHistorial(List<RegistroUsoContenedor> historial) { this.historial = historial; }
}