package external;

import com.dto.CapacidadPlantaDTO;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    
    @Override
    public void notificarAsignacion(int numContenedores, int totalKg, LocalDate fecha) {
        try {
            String url = baseUrl + "/notificacion";
            
            // Crear el JSON de la notificación
            String json = String.format(
                "{\"fecha\":\"%s\",\"numContenedores\":%d,\"totalEnvasesKg\":%d}",
                fecha.toString(),
                numContenedores,
                totalKg
            );
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            
            // Enviar datos
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            
            int responseCode = conn.getResponseCode();
            
            if (responseCode == 201) {
                System.out.println("[PlasSB] Notificación enviada correctamente");
            } else {
                System.err.println("[PlasSB] Error en notificación. Código: " + responseCode);
            }
            
        } catch (Exception e) {
            System.err.println("[PlasSB] Error notificación: " + e.getMessage());
            throw new RuntimeException("Error al notificar PlasSB", e);
        }
    }
}

