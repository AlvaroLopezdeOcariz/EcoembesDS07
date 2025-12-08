package external;

import com.dto.CapacidadPlantaDTO;

public interface ServiceGateway {
    CapacidadPlantaDTO consultarCapacidad(String plantaId);
   
}
