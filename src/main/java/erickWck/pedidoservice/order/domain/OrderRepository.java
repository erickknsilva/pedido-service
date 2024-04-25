package erickWck.pedidoservice.order.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface OrderRepository extends R2dbcRepository<Order, Long> {


}
