package external;

import com.dto.CapacidadPlantaDTO;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

public class ContSocketServiceGateway implements ServiceGateway {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public ContSocketServiceGateway(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public CapacidadPlantaDTO consultarCapacidad(LocalDate fecha) {

        // URL del servicio ContSocket
        String url = baseUrl + "/contsocket/capacidad?fecha={fecha}";

        try {
            Map<String, Object> response =
                    restTemplate.getForObject(url, Map.class, fecha.toString());

            CapacidadPlantaDTO dto = new CapacidadPlantaDTO();
            dto.setFecha(fecha);
            dto.setCapacidadDisponible(
                    ((Number) response.get("capacidadDisponible")).intValue()
            );

            return dto;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al consultar capacidad en ContSocket", e);
        }
    }
}
