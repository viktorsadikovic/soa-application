package mk.ukim.finki.soa.paymentservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto implements Serializable {

    private double price;

    private List<Long> workoutPrograms;
}
