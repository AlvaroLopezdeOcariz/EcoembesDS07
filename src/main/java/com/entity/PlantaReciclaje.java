	package com.entity;
	

	import jakarta.persistence.*;
	
	
	 @Entity
	 @Table(name = "planta_reciclaje")
	public class PlantaReciclaje {
	
		 @Id
	     @Column(nullable = false, unique = true)
		 private String id;
	        
	   
	    private String nombre;
	    @Column(name = "ubicacion")
	    private String ubicacion;
	    @Column(name = "maxima_capacidad")
	    private Integer maximaCapacidad;
	   
	    @Column(name = "codigo_postal")
	    private Integer codigoPostal;

	   

	       
	      

	        @Column(name = "tipo_servidor")
	        private String tipoServidor; 

	        @Column(name = "url_base")
	        private String urlBase; 

	        private Integer puerto; 
	    
	        public PlantaReciclaje() {}

	        public PlantaReciclaje(String id, String nombre, 
                    String tipoServidor, String urlBase, Integer puerto) {
	        	this.id = id;
	        	this.nombre = nombre;
	        	
	        	this.tipoServidor = tipoServidor;
	        	this.urlBase = urlBase;
	        	this.puerto = puerto;
}
	
	
	  
	
	   
	
	  
	
	    public String getId() { return id; }
	    public String getNombre() { return nombre; }
	    public String getUbicacion() { return ubicacion; }
	    public Integer getMaximaCapacidad() { return maximaCapacidad; }
	 
	    public Integer getCodigoPostal() { return codigoPostal; }
	  
	    public String getTipoServidor() { return tipoServidor; }
	    public String getUrlBase() { return urlBase; }
	    public Integer getPuerto() { return puerto; }
	    public void setId(String id) { this.id = id; }
	    public void setNombre(String nombre) { this.nombre = nombre; }
	    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
	    public void setMaximaCapacidad(Integer maximaCapacidad) { this.maximaCapacidad = maximaCapacidad; }
	   
	    public void setCodigoPostal(Integer codigoPostal) { this.codigoPostal = codigoPostal; }
	 
	    public void setTipoServidor(String tipoServidor) { this.tipoServidor = tipoServidor; }
	    public void setUrlBase(String urlBase) { this.urlBase = urlBase; }
	    public void setPuerto(Integer puerto) { this.puerto = puerto; }
	
	  
	}
