package external;

import com.dto.CapacidadPlantaDTO;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;

public class ContSocketSocketGateway implements ServiceGateway {

    private final String host;
    private final int port;

    public ContSocketSocketGateway(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public CapacidadPlantaDTO consultarCapacidad(LocalDate fecha) {

        String requestJson = """
        {
          "tipo":"CONSULTA_CAPACIDAD",
          "fecha":"%s"
        }
        """.formatted(fecha);

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {

            out.println(requestJson);
            String response = in.readLine();

            // respuesta esperada:
            // {"status":"OK","capacidadLibreKg":1234}

            int capacidad = Integer.parseInt(
                    response.split("capacidadLibreKg\":")[1]
                            .replaceAll("[^0-9]", "")
            );

            CapacidadPlantaDTO dto = new CapacidadPlantaDTO();
            dto.setFecha(fecha);
            dto.setCapacidadDisponible(capacidad);
            return dto;

        } catch (Exception e) {
            throw new RuntimeException("Error comunicando con ContSocket por socket", e);
        }
    }
}

