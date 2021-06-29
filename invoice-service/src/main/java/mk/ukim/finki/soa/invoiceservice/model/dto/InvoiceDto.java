package mk.ukim.finki.soa.invoiceservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

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
}
