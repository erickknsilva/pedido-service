package erickWck.pedidoservice.product;

import erickWck.pedidoservice.config.ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class ProductClient {

    private final static String PRODUCT_ROUTE_API = "/products/find/";

    private final WebClient webClient;
    private final ClientProperties clientProperties;

    public ProductClient(WebClient webClient, ClientProperties clientProperties) {
        this.webClient = webClient;
        this.clientProperties = clientProperties;
    }

    public Mono<ProductResponse> getProductId(Long id) {

        return webClient.get()
                .uri(PRODUCT_ROUTE_API + id)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .timeout(Duration.ofSeconds(clientProperties.tempLimit()), Mono.empty())
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(Exception.class, ex -> Mono.empty());
    }


}
