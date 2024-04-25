package erickWck.pedidoservice.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

@ConfigurationProperties(prefix = "loja")
public record ClientProperties(

        @NotNull
        URI catalogServiceUri,
        Long tempLimit
) {

}
