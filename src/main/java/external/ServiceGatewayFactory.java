package external;

import proxies.ContSocketServiceGateway;
import proxies.PlasSBServiceGateway;

public class ServiceGatewayFactory {

    public static ServiceGateway create(String type) {

        return switch (type) {
            case "PLASSB" ->
                new PlasSBServiceGateway("http://localhost:8081/api/plassb");

            case "CONTSOCKET" ->
                new ContSocketServiceGateway("localhost", 9000);
                
		default -> throw new IllegalArgumentException("Unexpected value: " + type);

            
        };
    }
}
