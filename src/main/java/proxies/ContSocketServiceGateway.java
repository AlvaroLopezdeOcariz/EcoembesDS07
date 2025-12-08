package proxies;
import com.dto.CapacidadPlantaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import external.ServiceGateway;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ContSocketServiceGateway implements ServiceGateway {

    private final String host;
    private final int port;
    private final ObjectMapper mapper = new ObjectMapper();

    public ContSocketServiceGateway(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public CapacidadPlantaDTO consultarCapacidad(String plantaId) {

        try (Socket socket = new Socket(host, port)) {

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // Enviar petición JSON al servidor socket
            writer.println("{\"action\":\"GET_CAPACITY\",\"plantId\":" + plantaId + "}");

            // Leer respuesta JSON
            String responseJson = reader.readLine();

            Map<String, Object> data = mapper.readValue(responseJson, Map.class);

            if (!"OK".equals(data.get("status"))) {
                return null;
            }

            CapacidadPlantaDTO dto = new CapacidadPlantaDTO();
            dto.setPlantaId(plantaId);
            dto.setCapacidadDisponible((int) data.get("capacidadLibreKg"));
           

            return dto;

        } catch (Exception e) {
            System.err.println("ERROR en ContSocketServiceGateway.consultarCapacidad(): " + e.getMessage());
            return null;
        }
    }


 
}
