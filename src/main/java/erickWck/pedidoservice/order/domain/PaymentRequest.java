package erickWck.pedidoservice.order.domain;

import lombok.Builder;

@Builder
public record PaymentRequest(

        String type,
        String cardNumber,
        String expiryDate,
        String cvv,
        String cardholderName

) {

    public static PaymentRequest of(String type, String cardNumber, String expiryDate,
                                    String cvv, String cardholderName) {
        return new PaymentRequest(type, cardNumber, expiryDate, cvv, cardholderName);
    }


}
