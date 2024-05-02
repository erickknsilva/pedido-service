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


    public Mono<List<Order>> sendOrder(OrdersRequest orderRequest) {

        return productClient
                .getProductId(orderRequest)
                .map(listOrder -> dispatcherPayment(listOrder, orderRequest.paymentRequest()))
                .flatMap(orders -> orderServiceQuery.processAndSaveOrders(orders));
    }


    public List<ProductResponse> dispatcherPayment(List<ProductResponse> listOrder,
                                                   PaymentRequest paymentRequest) {

        Long orderId = orderServiceQuery.generatePositiveOrderId();

        BigDecimal amountFinal = orderServiceQuery.calculatorAmountFinal(listOrder);

        var paymentDispatcherMessage =
                PaymentDispatcherMessage.of(amountFinal, paymentRequest.type(),
                        paymentRequest.cardNumber(), paymentRequest.expiryDate(),
                        paymentRequest.cvv(), paymentRequest.cardholderName(),
                        orderId);


        log.info("Enviando o pagamento para processo {}", paymentDispatcherMessage);
        streamBridge.send("paymentProcess-out-0", paymentDispatcherMessage);

        log.info("Enviando para processadora com sucesso.", paymentDispatcherMessage);
        return listOrder.stream().collect(Collectors.toList());
    }

}
