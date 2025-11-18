package com.dto;

import java.util.Date;

public class AsignacionResultadoDTO {

    private String id;
    private String nombrePlanta;
    private Date fecha;
    private Integer numContenedores;
    private Integer numEnvases;

    public AsignacionResultadoDTO() {}
    public AsignacionResultadoDTO(String id, String nombrePlanta, Date fecha, int numContenedores, int numEnvases) {
		this.id = id;
		this.nombrePlanta = nombrePlanta;
		this.fecha = fecha;
		this.numContenedores = numContenedores;
		this.numEnvases = numEnvases;
	}

    public String getId() { return id; }
    public String getNombrePlanta() { return nombrePlanta; }
    public Date getFecha() { return fecha; }
    public int getNumContenedores() { return numContenedores; }
    public int getNumEnvases() { return numEnvases; }
}
