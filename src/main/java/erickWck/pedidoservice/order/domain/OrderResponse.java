package erickWck.pedidoservice.order.domain;

import java.math.BigDecimal;

public record OrderResponse(


        Long orderId,

        Long productId,

        String name,

        String marca,

        BigDecimal preco,

        OrderStatus status


) {

    public static OrderResponse fromEntityToDto(Order order) {

        return new OrderResponse(order.orderId(), order.productId(), order.name(), order.marca(), order.preco(), order.status());
    }

}