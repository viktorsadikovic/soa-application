package mk.ukim.finki.soa.invoiceservice.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
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
