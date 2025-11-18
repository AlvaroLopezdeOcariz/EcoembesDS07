package com.dto;

import java.util.Date;

public class RegistroUsoDTO {

    private String id;
    private Date fecha;
    private String nivelLlenado;

    public RegistroUsoDTO() {}

    public RegistroUsoDTO(String id, Date fecha, String nivelLlenado) {
        this.id = id;
        this.fecha = fecha;
        this.nivelLlenado = nivelLlenado;
    }

    public String getId() { return id; }
    public Date getFecha() { return fecha; }
    public String getNivelLlenado() { return nivelLlenado; }
}
