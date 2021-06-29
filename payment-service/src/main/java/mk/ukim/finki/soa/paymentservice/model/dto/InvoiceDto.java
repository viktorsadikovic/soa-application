package mk.ukim.finki.soa.paymentservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.soa.paymentservice.model.Order;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class InvoiceDto implements Serializable {

    private Long id;

    private LocalDateTime dateTime;

    private Long orderId;

    private String name;

    private String surname;

    private double price;

    private String cardNumber;

    private List<WorkoutProgramDto> workoutPrograms;

    public InvoiceDto(LocalDateTime dateTime, Long orderId, String name, String surname, double price, String cardNumber, List<WorkoutProgramDto> workoutPrograms) {
        this.dateTime = dateTime;
        this.orderId = orderId;
        this.name = name;
        this.surname = surname;
        this.price = price;
        this.cardNumber = cardNumber;
        this.workoutPrograms = workoutPrograms;
    }

    public static InvoiceDto create(Order order, List<WorkoutProgramDto> workoutPrograms, String name, String surname, String cardNumber) {
        return new InvoiceDto(LocalDateTime.now(), order.getId(), name, surname, order.getPrice(), cardNumber, workoutPrograms);
    }
}
