package mk.ukim.finki.soa.workoutprogramsservice.model.dto;

import lombok.Data;
import mk.ukim.finki.soa.workoutprogramsservice.model.Exercise;

@Data
public class ExerciseWrapperDto {

    private Long exerciseId;

    private Integer numberOfSets;

    private Integer numberOfReps;
}
