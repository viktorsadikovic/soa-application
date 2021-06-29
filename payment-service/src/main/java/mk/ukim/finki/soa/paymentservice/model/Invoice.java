package mk.ukim.finki.soa.paymentservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Invoice {
    private Long id;

    private LocalDateTime dateTime;

    private Long orderId;

    private String name;

    private String surname;

    private String price;

    private String cardNumber;
}
