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

    @Override
    public String toString() {
        return "ProductResponse{" +
                "ids=" + id +
                ", name='" + name + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}