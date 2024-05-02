package erickWck.pedidoservice.order.event;

import java.math.BigDecimal;

public record PaymentDispatcherMessage(

        BigDecimal amount,
        String type,
        String cardNumber,
        String expiryDate,
        String cvv,
        String cardholderName,
        Long orderId

) {

    public static PaymentDispatcherMessage of(BigDecimal amount, String type,
                                              String cardNumber, String expiryDate,
                                              String cvv, String cardholderName, Long orderId) {
        return new PaymentDispatcherMessage(amount, type,
                cardNumber, expiryDate, cvv, cardholderName, orderId);
    }


    @Override
    public String toString() {
        return "PaymentDispatcherMessage{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", cardholderName='" + cardholderName + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
