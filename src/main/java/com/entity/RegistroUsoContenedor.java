package com.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registro_uso_contenedor")
public class RegistroUsoContenedor {
    
    @Id
    @Column(name = "id", length = 50)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "contenedor_id", nullable = false)
    private Contenedor contenedor;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "nivel_llenado")
    private Integer nivelLlenado;
    
    @Column(name = "num_envases")
    private Integer numEnvases;
    
    // Constructores
    public RegistroUsoContenedor() {}
    
    public RegistroUsoContenedor(String id, Date fecha, Integer nivelLlenado, Integer numEnvases) {
        this.id = id;
        this.fecha = fecha;
        this.nivelLlenado = nivelLlenado;
        this.numEnvases = numEnvases;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public Contenedor getContenedor() { return contenedor; }
    public void setContenedor(Contenedor contenedor) { this.contenedor = contenedor; }
    
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
    public Integer getNivelLlenado() { return nivelLlenado; }
    public void setNivelLlenado(Integer nivelLlenado) { this.nivelLlenado = nivelLlenado; }
    
    public Integer getNumEnvases() { return numEnvases; }
    public void setNumEnvases(Integer numEnvases) { this.numEnvases = numEnvases; }
}