package mk.ukim.finki.soa.invoiceservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.soa.invoiceservice.model.dto.InvoiceDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private Long orderId;

    private String name;

    private String surname;

    private double price;

    private String cardNumber;

    public Invoice(LocalDateTime dateTime, Long orderId, String name, String surname, double price, String cardNumber) {
        this.dateTime = dateTime;
        this.orderId = orderId;
        this.name = name;
        this.surname = surname;
        this.price = price;
        this.cardNumber = cardNumber;
    }

    public static Invoice create(InvoiceDto invoiceDto) {
        return new Invoice(LocalDateTime.now(), invoiceDto.getOrderId(), invoiceDto.getName(), invoiceDto.getSurname(),
                          invoiceDto.getPrice(), invoiceDto.getCardNumber());
    }

}
