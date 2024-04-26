package erickWck.pedidoservice.order.domain;

import erickWck.pedidoservice.order.web.OrdersRequest;
import erickWck.pedidoservice.product.ProductClient;
import erickWck.pedidoservice.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public void sendOrder(OrdersRequest ordersRequest) {
        productClient
                .getProductId(ordersRequest)
                .flatMap(this::processAndSaveOrders)
                .subscribe(
                        productResponse -> {
                            System.out.println(productResponse.toString());
                        },
                        error -> System.err.println("Erro: " + error.getMessage())
                );
    }

    private Mono<List<Order>> processAndSaveOrders(List<ProductResponse> productResponses) {
        List<Order> orders = convertToOrders(productResponses);
        return orderRepository.saveAll(orders).collectList();
    }

    private List<Order> convertToOrders(List<ProductResponse> responses) {
        return responses.stream()
                .map(response -> Order.of(null, response.id(), response.name(), response.preco(), OrderStatus.ACCEPTED))
                .collect(Collectors.toList());
    }


}
