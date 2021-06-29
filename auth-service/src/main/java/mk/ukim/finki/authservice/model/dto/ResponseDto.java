package mk.ukim.finki.authservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.authservice.model.User;

@Data
@NoArgsConstructor
public class ResponseDto {

    private Integer statusCode;

    private String message;

    private User user;

    private String token;
}

