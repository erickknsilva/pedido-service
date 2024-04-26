package erickWck.pedidoservice.order.domain;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Table(name = "orders")
public record Order(

        @Id
        Long id,

        @NotNull(message = "Insira o Identificador do produto")
        Long productId,

        String name,

        String marca,

        BigDecimal preco,

        OrderStatus status,

        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @Version
        int version

) {

    public static Order of(Long id, Long productId, String productName, String marca,
                           BigDecimal preco, OrderStatus status) {

        return new Order(null, productId, productName, marca, preco,
                status, null, null, 0);
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", preco=" + preco +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", version=" + version +
                '}';
    }
}
