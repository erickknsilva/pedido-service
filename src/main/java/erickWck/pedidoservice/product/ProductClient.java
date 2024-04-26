package erickWck.pedidoservice.product;

import erickWck.pedidoservice.config.ClientProperties;
import erickWck.pedidoservice.order.web.OrdersRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Component
public class ProductClient {

    private final static String PRODUCT_ROUTE_API = "/products/submit";

    private final WebClient webClient;
    private final ClientProperties clientProperties;

    public ProductClient(WebClient webClient, ClientProperties clientProperties) {
        this.webClient = webClient;
        this.clientProperties = clientProperties;
    }

    public Mono<List<ProductResponse>> getProductId(OrdersRequest ordersRequest) {
        validationList(ordersRequest);
        return webClient.post()
                .uri(PRODUCT_ROUTE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(ordersRequest)
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList()
                .timeout(Duration.ofSeconds(clientProperties.tempLimit()),
                        Mono.empty())
                .retryWhen(Retry.backoff(3, Duration.ofMillis(200)))
                .onErrorResume(Exception.class, ex -> Mono.empty());

    }

    private void validationList(OrdersRequest request) {
        if (request.idProducts().isEmpty()) {
            throw new IllegalArgumentException("A lista deve conter pelo menos um pedido.");
        }
        if (request.idProducts().size() >= 5) {
            throw new IllegalArgumentException("Você só pode fazer no máximo 5 pedidos.");
        }
    }


}
