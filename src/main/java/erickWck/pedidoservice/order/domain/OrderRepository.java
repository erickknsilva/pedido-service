package erickWck.pedidoservice.order.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderRepository extends R2dbcRepository<Order, Long> {

//    Mono<List<Order>> saveAll(List<Order> orders);
}
