package factory;


import external.ContSocketSocketGateway;
import external.PlasSBServiceGateway;
import external.ServiceGateway;

public class ServiceGatewayFactory {

    public static ServiceGateway create(
            String tipoServidor,
            String urlBase,
            Integer puerto) {

        return switch (tipoServidor) {

            case "PLASSB" -> {
                // 🔹 Caso antiguo: url_base YA es completa
                if (puerto == null) {
                    yield new PlasSBServiceGateway(urlBase);
                }
                // 🔹 Caso nuevo: host + puerto
                yield new PlasSBServiceGateway(urlBase + ":" + puerto);
            }

            case "CONTSOCKET" -> {
                // 🔹 ContSocket SIEMPRE es REST
            	yield new ContSocketSocketGateway(urlBase, puerto);
            }

            default -> throw new IllegalArgumentException(
                    "Tipo de servidor no soportado: " + tipoServidor
            );
        };
    }
}

