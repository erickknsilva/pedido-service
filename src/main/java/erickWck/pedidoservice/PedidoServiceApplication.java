package erickWck.pedidoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PedidoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidoServiceApplication.class, args);
    }

}
