package factory;

import external.ServiceGateway;
import proxies.ContSocketServiceGateway;
import proxies.PlasSBServiceGateway;

public class ServiceGatewayFactory {

    public static ServiceGateway create(String type, String urlBase, Integer puerto) {

        return switch (type) {
            case "PLASSB" ->
                new PlasSBServiceGateway(urlBase);

            case "CONTSOCKET" -> 
                
                 new ContSocketServiceGateway(urlBase, puerto);
            
		default -> throw new IllegalArgumentException("Unexpected value: " + type);

            
        };
    }
}
