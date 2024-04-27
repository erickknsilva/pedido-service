package erickWck.pedidoservice.order.event;

import java.math.BigDecimal;

public record PaymentDispatcherMessage(

        BigDecimal amount,
        String type,
        String cardNumber,
        String expiryDate,
        String cvv,
        String cardholderName

) {

    public static PaymentDispatcherMessage of(BigDecimal amount, String type,
                                              String cardNumber, String expiryDate,
                                              String cvv, String cardholderName) {
        return new PaymentDispatcherMessage(amount, type,
                cardNumber, expiryDate, cvv, cardholderName);
    }


//        "PaymentRequest":{
//        "type":"credit_card",  // Tipo de método de pagamento
//                "cardNumber":"4111111111111111",  // Número do cartão (apenas um exemplo)
//                "expiryDate":"12/24",  // Data de expiração
//                "cvv":"123",  // CVV do cartão
//                "cardholderName":"John Doe"  // Nome do titular do cartão
//    }


    @Override
    public String toString() {
        return "PaymentDispatcherMessage{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", cardholderName='" + cardholderName + '\'' +
                '}';
    }
}
