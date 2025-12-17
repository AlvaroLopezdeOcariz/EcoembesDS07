package external;

import com.dto.CapacidadPlantaDTO;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class PlasSBServiceGateway implements ServiceGateway {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * @param baseUrl URL COMPLETA del servicio PlasSB.
     *                Ejemplo: http://localhost:8081/plassb
     */
    public PlasSBServiceGateway(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public CapacidadPlantaDTO consultarCapacidad(LocalDate fecha) {

        // Construcción segura de la URL
        String url = baseUrl + "/capacidad?fecha={fecha}";

        try {
            return restTemplate.getForObject(
                    url,
                    CapacidadPlantaDTO.class,
                    fecha.toString()
            );

        } catch (Exception e) {
            // Log explícito para depuración
            System.err.println("[PlasSBServiceGateway] Error llamando a " + url);
            e.printStackTrace();
            return null;
        }
    }
}

