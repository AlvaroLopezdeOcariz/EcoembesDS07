package com.AppServices;

import com.dto.ContenedorHistorialDTO;
import com.dto.ContenedorZonaDTO;
import com.dto.CrearContenedorDTO;
import com.dto.ActualizarContenedorDTO;
import com.dto.RegistroUsoDTO;
import com.entity.Contenedor;
import com.entity.RegistroUsoContenedor;
import dao.ContenedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContenedorService {

    private final ContenedorRepository contenedorRepository;

    public ContenedorService(ContenedorRepository contenedorRepository) {
        this.contenedorRepository = contenedorRepository;
    }

    /**
     * Crear un nuevo contenedor
     */
    public Contenedor crearContenedor(CrearContenedorDTO dto) {
        
        if (dto.getId() == null || dto.getId().isEmpty()) {
            throw new IllegalArgumentException("ID del contenedor obligatorio");
        }
        
        if (dto.getUbicacion() == null || dto.getUbicacion().isEmpty()) {
            throw new IllegalArgumentException("Ubicación obligatoria");
        }
        
        if (dto.getCodigoPostal() == null || dto.getCodigoPostal().isEmpty()) {
            throw new IllegalArgumentException("Código postal obligatorio");
        }

        int cp = Integer.parseInt(dto.getCodigoPostal());

        Contenedor contenedor = new Contenedor(
            dto.getId(),
            dto.getUbicacion(),
            dto.getCapacidad(),
            cp
        );

        return contenedorRepository.save(contenedor);
    }

    /**
     * Actualizar contenedor (simula lectura de sensor)
     */
    /**
     * Actualizar contenedor (simula lectura de sensor)
     */
    @Transactional  // AÑADIDO
    public Contenedor actualizarContenedor(String id, ActualizarContenedorDTO dto) {
        
        Contenedor contenedor = contenedorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contenedor no encontrado: " + id));
        
        // Actualizar valores actuales
        contenedor.setNivelLlenado(dto.getNivelLlenado());
        contenedor.setNumEnvases(dto.getNumEnvases());
        
        // Crear nuevo registro en el historial
        RegistroUsoContenedor registro = new RegistroUsoContenedor(
            UUID.randomUUID().toString(),
            new Date(),
            dto.getNivelLlenado(),
            dto.getNumEnvases()
        );
        
        registro.setContenedor(contenedor);
        contenedor.getHistorial().add(registro);  // ✅ Ahora funciona
        
        // Guardar (cascade guardará también el registro)
        return contenedorRepository.save(contenedor);
    }
    /**
     * Consultar historial de un contenedor entre fechas
     */
    @Transactional(readOnly = true)
    public ContenedorHistorialDTO consultarContenedor(String id, Date inicio, Date fin) {
        
        // Buscar el contenedor por ID
        Contenedor contenedor = contenedorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contenedor no encontrado: " + id));
        
        // Filtrar el historial entre las fechas
        List<RegistroUsoContenedor> historialFiltrado = new ArrayList<>();
        
        for (RegistroUsoContenedor registro : contenedor.getHistorial()) {
            Date fechaRegistro = registro.getFecha();
            
            // Verificar que la fecha esté en el rango
            if ((fechaRegistro.equals(inicio) || fechaRegistro.after(inicio)) &&
                (fechaRegistro.equals(fin) || fechaRegistro.before(fin))) {
                historialFiltrado.add(registro);
            }
        }
        
        // Convertir registros a DTOs
        List<RegistroUsoDTO> historialDTO = new ArrayList<>();
        for (RegistroUsoContenedor registro : historialFiltrado) {
            RegistroUsoDTO dto = new RegistroUsoDTO(
                registro.getFecha(),
                registro.getNivelLlenado(),
                registro.getNumEnvases()
            );
            historialDTO.add(dto);
        }
        
        // Crear y devolver el DTO completo
        return new ContenedorHistorialDTO(
            contenedor.getId(),
            contenedor.getUbicacion(),
            String.valueOf(contenedor.getCodigoPostal()),
            contenedor.getCapacidad(),
            inicio,
            fin,
            historialDTO
        );
    }

    /**
     * Buscar contenedores por zona (código postal)
     */
    public List<ContenedorZonaDTO> buscarPorZona(String codigoPostal, Date fecha) {
        
        if (codigoPostal == null || codigoPostal.isEmpty()) {
            throw new IllegalArgumentException("Código postal obligatorio");
        }
        
        int cp = Integer.parseInt(codigoPostal);
        
        // USAR MÉTODO CON JOIN FETCH (carga historial automáticamente)
        List<Contenedor> contenedores = contenedorRepository.findByCodigoPostalWithHistorial(cp);

        List<ContenedorZonaDTO> resultado = new ArrayList<>();

        for (Contenedor c : contenedores) {
            // Ahora el historial YA ESTÁ CARGADO, no hay LazyInitializationException
            RegistroUsoContenedor registro = buscarRegistroEnFecha(c, fecha);
            
            if (registro != null) {
            	// ✅ Validar valores del registro (por si acaso)
                Integer nivelLlenadoReg = registro.getNivelLlenado() != null ? registro.getNivelLlenado() : 0;
                Integer numEnvasesReg = registro.getNumEnvases() != null ? registro.getNumEnvases() : 0;
                
                String estado = calcularEstado(nivelLlenadoReg, c.getCapacidad());
                
                ContenedorZonaDTO dto = new ContenedorZonaDTO(
                    c.getId(),
                    c.getUbicacion(),
                    c.getCapacidad(),
                    nivelLlenadoReg,      // ✅ Nunca null
                    numEnvasesReg,        // ✅ Nunca null
                    estado,
                    fecha
                );
                
                resultado.add(dto);
            } else {
            	// Si no hay registro, usar valores actuales (con validación null)
                Integer nivelLlenado = c.getNivelLlenado() != null ? c.getNivelLlenado() : 0;
                Integer numEnvases = c.getNumEnvases() != null ? c.getNumEnvases() : 0;
                
                String estado = calcularEstado(nivelLlenado, c.getCapacidad());
                
                ContenedorZonaDTO dto = new ContenedorZonaDTO(
                    c.getId(),
                    c.getUbicacion(),
                    c.getCapacidad(),
                    nivelLlenado,    // ✅ Nunca null (0 por defecto)
                    numEnvases,      // ✅ Nunca null (0 por defecto)
                    estado,
                    fecha
                );
                
                resultado.add(dto);
            }
        }
        
        return resultado;
    }

    /**
     * Calcular estado según porcentaje de llenado
     */
    private String calcularEstado(Integer nivelLlenado, Integer capacidad) {
        // Validar que ningún valor sea null o cero
        if (nivelLlenado == null || capacidad == null || capacidad == 0) {
            return "VERDE";  // Estado por defecto
        }
        
        double porcentaje = (double) nivelLlenado / capacidad * 100;
        
        if (porcentaje >= 80) {
            return "ROJO";
        } else if (porcentaje >= 50) {
            return "NARANJA";
        } else {
            return "VERDE";
        }
    }

    /**
     * Buscar el registro de uso más cercano a una fecha
     */
    private RegistroUsoContenedor buscarRegistroEnFecha(Contenedor contenedor, Date fecha) {
        List<RegistroUsoContenedor> historial = contenedor.getHistorial();
        
        if (historial == null || historial.isEmpty()) {
            return null;
        }
        
        RegistroUsoContenedor masReciente = null;
        long menorDiferencia = Long.MAX_VALUE;
        
        for (RegistroUsoContenedor registro : historial) {
        	if (registro.getFecha() == null) {
                continue;  // Saltar este registro
            }
            long diferencia = Math.abs(fecha.getTime() - registro.getFecha().getTime());
            if (diferencia < menorDiferencia) {
                menorDiferencia = diferencia;
                masReciente = registro;
            }
        }
        
        return masReciente;
    }
}