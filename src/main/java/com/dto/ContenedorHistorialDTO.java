package com.dto;

import java.util.Date;
import java.util.List;

public class ContenedorHistorialDTO {

    private String idContenedor;
    private String ubicacion;
    private String codigoPostal;
    private Integer capacidadMaxima;
    private Date fechaInicio;
    private Date fechaFin;

    
    private List<RegistroUsoDTO> historial;

    public ContenedorHistorialDTO() {}

    public ContenedorHistorialDTO(String idContenedor,
                                  String ubicacion,
                                  String codigoPostal,
                                  Integer capacidadMaxima,
                                  Date fechaInicio,
                                  Date fechaFin,
                                  List<RegistroUsoDTO> historial) {

        this.idContenedor = idContenedor;
        this.ubicacion = ubicacion;
        this.codigoPostal = codigoPostal;
        this.capacidadMaxima = capacidadMaxima;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.historial = historial;
    }

    public String getIdContenedor() { return idContenedor; }
    public String getUbicacion() { return ubicacion; }
    public String getCodigoPostal() { return codigoPostal; }
    public Integer getCapacidadMaxima() { return capacidadMaxima; }
    public Date getFechaInicio() { return fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public List<RegistroUsoDTO> getHistorial() { return historial; }
}
