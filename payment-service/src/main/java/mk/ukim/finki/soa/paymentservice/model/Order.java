package mk.ukim.finki.soa.paymentservice.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import mk.ukim.finki.soa.paymentservice.model.enumeration.OrderState;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private double price;

    @ElementCollection
    private List<Long> workoutPrograms;

    public Order addItem(@NotNull Long workoutProgramId) {
        Objects.requireNonNull(workoutProgramId, "Product must not be null");
        workoutPrograms.add(workoutProgramId);

        return this;
    }

    public void removeItem(@NonNull Long workoutProgramId) {
        Objects.requireNonNull(workoutProgramId, "Order Item must not be null");
        workoutPrograms.removeIf(item -> item.equals(workoutProgramId));
    }
}
