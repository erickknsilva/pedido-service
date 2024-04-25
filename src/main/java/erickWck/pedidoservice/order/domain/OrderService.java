package erickWck.pedidoservice.order.domain;

import erickWck.pedidoservice.product.ProductClient;
import erickWck.pedidoservice.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public void sendOrder(Long id, Integer quantidade) {
        productClient.getProductId(id)
                .subscribe(
                        productResponse -> System.out.println(productResponse.toString()), // Sucesso
                        error -> System.err.println("Erro: " + error.getMessage()) // Falha
                );
    }


}
