package erickWck.pedidoservice.order.web;

import erickWck.pedidoservice.order.domain.Order;
import erickWck.pedidoservice.order.domain.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<List<Order>> submitOrder(@Valid @RequestBody OrdersRequest orderRequest) {
        return orderService.sendOrder(orderRequest);
    }

}
