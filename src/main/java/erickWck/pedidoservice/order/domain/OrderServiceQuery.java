package erickWck.pedidoservice.order.domain;

import erickWck.pedidoservice.product.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderServiceQuery {

    private final OrderRepository orderRepository;


    public Mono<List<Order>> processAndSaveOrders(List<ProductResponse> productResponses) {

        List<Order> orders = convertToOrders(generatePositiveOrderId(), productResponses);
        return orderRepository.saveAll(orders).collectList();
    }

    private List<Order> convertToOrders(Long orderId, List<ProductResponse> responses) {
        return responses.stream()
                .map(response -> Order.of(null, orderId, response.id(), response.name(),
                        response.marca(), response.preco(), OrderStatus.ACCEPTED))
                .collect(Collectors.toList());
    }


    private long generatePositiveOrderId() {

        // Gere um UUID aleatório
        UUID uuid = UUID.randomUUID();

        // Extraia a parte menos significativa (64 bits) como long
        long leastSignificantBits = uuid.getLeastSignificantBits();

        // Converta para um número positivo
        long positiveOrderId = Math.abs(leastSignificantBits);

        return positiveOrderId;
    }

    public BigDecimal calculatorAmountFinal(List<ProductResponse> pay) {
        return pay.stream().map(p -> p.preco())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
