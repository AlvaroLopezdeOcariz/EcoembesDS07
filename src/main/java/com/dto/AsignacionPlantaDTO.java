package com.dto;

import java.util.Date;
import java.util.List;
import com.entity.Contenedor;

public class AsignacionPlantaDTO {

    private String nombre;
    private Date fecha;
    private List<Contenedor> listaContenedores;
    private String attribute;

    public AsignacionPlantaDTO() {}

    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<Contenedor> getListaContenedores() {
        return listaContenedores;
    }

    public String getAttribute() {
        return attribute;
    }
}
