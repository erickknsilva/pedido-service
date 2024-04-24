package erickWck.pedidoservice.product;

import java.math.BigDecimal;

public record ProductResponse(

        Long id,
        String name,
        String modelo,
        String marca,
        BigDecimal preco,
        String descricao

) {


}