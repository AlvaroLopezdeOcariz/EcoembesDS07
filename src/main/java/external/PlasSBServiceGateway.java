package external;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dto.CapacidadPlantaDTO;

public class PlasSBServiceGateway implements ServiceGateway {

	@Value("${external.plassb.base-url}")
	private String baseUrl;
    private final RestTemplate restTemplate;

    public PlasSBServiceGateway(String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
    }



    @Override
    public CapacidadPlantaDTO consultarCapacidad(String fecha) {

        // Endpoint correcto de PlasSB
        String url = baseUrl + "/capacidad?fecha={fecha}";

        try {
            ResponseEntity<CapacidadPlantaDTO> response =
                    restTemplate.getForEntity(
                            url,
                            CapacidadPlantaDTO.class,
                            fecha
                    );

            return response.getBody();

        } catch (Exception e) {
            System.err.println(
                "ERROR en PlasSBServiceGateway.consultarCapacidad(): " + e.getMessage()
            );
            return null;
        }
    }

    
}