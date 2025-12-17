package external;

import java.time.LocalDate;

import org.springframework.web.client.RestTemplate;

import com.dto.CapacidadPlantaDTO;

public class PlasSBServiceGateway implements ServiceGateway {

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public PlasSBServiceGateway(String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public CapacidadPlantaDTO consultarCapacidad(LocalDate fecha) {

        // Endpoint de PlasSB
        String url = baseUrl + "/capacidad?fecha={fecha}";

        try {
            return restTemplate.getForObject(
                    url,
                    CapacidadPlantaDTO.class,
                    fecha.toString() // YYYY-MM-DD
            );

        } catch (Exception e) {
            System.err.println(
                "ERROR en PlasSBServiceGateway.consultarCapacidad(): " + e.getMessage()
            );
            return null;
        }
    }
}
