package mk.ukim.finki.soa.workoutprogramsservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.soa.workoutprogramsservice.model.ExerciseWrapper;
import mk.ukim.finki.soa.workoutprogramsservice.model.enumeration.BodyPart;
import mk.ukim.finki.soa.workoutprogramsservice.model.enumeration.WorkoutType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class WorkoutProgramDto {

    private String creator;

    private String title;

    private String description;

    private String image;

    private LocalDateTime submissionTime;

    private WorkoutType workoutType;

    private Boolean equipment;

    private BodyPart bodyPart;

    private List<ExerciseWrapperDto> exerciseWrappers;

    private Double price = 0.0;
}
