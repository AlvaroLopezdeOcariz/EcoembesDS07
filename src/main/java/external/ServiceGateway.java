package external;

import java.time.LocalDate;
import com.dto.CapacidadPlantaDTO;

public interface ServiceGateway {

    CapacidadPlantaDTO consultarCapacidad(LocalDate fecha);
    
    void notificarAsignacion(int numContenedores, int totalKg, LocalDate fecha);
}

