package erickWck.pedidoservice.order.web;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrdersRequest(

        @NotNull(message = "Insira o identificador do produto.")
        List<Long> idProducts
) {
}
