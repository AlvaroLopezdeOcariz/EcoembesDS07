package external;

import java.time.LocalDate;
import com.dto.CapacidadPlantaDTO;

public interface ServiceGateway {

    CapacidadPlantaDTO consultarCapacidad(LocalDate fecha);
}

