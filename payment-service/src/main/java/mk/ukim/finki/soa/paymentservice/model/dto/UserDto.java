package mk.ukim.finki.soa.paymentservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String cardNumber;
}
