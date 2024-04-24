package erickWck.pedidoservice.domain;

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

        String productName,

        BigDecimal preco,

        String status,

        Integer quantity,

        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @Version
        int version

) {

}
