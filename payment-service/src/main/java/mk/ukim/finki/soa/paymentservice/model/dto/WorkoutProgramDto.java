package mk.ukim.finki.soa.paymentservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class WorkoutProgramDto implements Serializable {

    private Long id;

    private String creator;

    private String title;

    private String description;

    private String image;

    private LocalDateTime submissionTime;

    private String workoutType;

    private Boolean equipment;

    private String bodyPart;

    private List<Object> exercises;

    private Double price = 0.0;

}
