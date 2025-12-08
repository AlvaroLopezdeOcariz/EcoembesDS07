package proxies;



import external.ServiceGateway;

import org.springframework.http.ResponseEntity;
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
    public CapacidadPlantaDTO consultarCapacidad(String plantaId) {
        String url = baseUrl + "/plants/" + plantaId + "/capacity";
        try {
            ResponseEntity<CapacidadPlantaDTO> response =
                    restTemplate.getForEntity(url, CapacidadPlantaDTO.class);

            return response.getBody();
        } catch (Exception e) {
            System.err.println("ERROR en PlasSBServiceGateway.consultarCapacidad(): " + e.getMessage());
            return null;
        }
    }
    
    
}