package erickWck.pedidoservice.order.web;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(

        @NotNull(message = "Insira o identificador do produto.")
        Long id,

        @NotNull(message = "A quantidade do produto não foi definida.")
        @Min(value = 1, message = "Você deve inserir pelo menos 1 produto.")
        @Max(value = 5, message = "Você só pode fazer no máximo 5 pedidos.")
        Integer quantity
) {
}
