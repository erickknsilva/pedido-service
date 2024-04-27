package erickWck.pedidoservice.order.domain;

import erickWck.pedidoservice.order.event.PaymentDispatcherMessage;
import erickWck.pedidoservice.order.web.OrdersRequest;
import erickWck.pedidoservice.product.ProductClient;
import erickWck.pedidoservice.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final StreamBridge streamBridge;
    private final ProductClient productClient;
    private final OrderServiceQuery orderServiceQuery;

    public Mono<List<Order>> sendOrder(OrdersRequest ordersRequest) {

        return productClient
                .getProductId(ordersRequest)
                .map(pay -> processPayment(pay, ordersRequest.paymentRequest()))
                .flatMap(orders -> orderServiceQuery.processAndSaveOrders(orders));
    }


    public List<ProductResponse> processPayment(List<ProductResponse> pay,
                                                PaymentRequest paymentRequest) {

        BigDecimal amountFinal = orderServiceQuery.calculatorAmountFinal(pay);

        var paymentDispatcherMessage =
                PaymentDispatcherMessage.of(amountFinal, paymentRequest.type(),
                        paymentRequest.cardNumber(), paymentRequest.expiryDate(),
                        paymentRequest.cvv(), paymentRequest.cardholderName());

        log.info("Enviando o pagamento para processo", paymentDispatcherMessage.toString());

        streamBridge.send("paymentProcess-out-0", paymentDispatcherMessage);
        log.info("Enviando com sucesso.", paymentDispatcherMessage.toString());

        return pay.stream().collect(Collectors.toList());
    }


}
