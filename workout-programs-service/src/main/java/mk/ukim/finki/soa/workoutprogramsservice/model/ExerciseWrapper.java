package mk.ukim.finki.soa.workoutprogramsservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "exercise_wrappers")
public class ExerciseWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    private Exercise exercise;

    private Integer numberOfSets;

    private Integer numberOfReps;

    public ExerciseWrapper(Exercise exercise, Integer numberOfSets, Integer numberOfReps) {
        this.exercise = exercise;
        this.numberOfSets = numberOfSets;
        this.numberOfReps = numberOfReps;
    }

    public static ExerciseWrapper create(Exercise exercise, Integer numberOfSets, Integer numberOfReps) {
        return new ExerciseWrapper(exercise, numberOfSets, numberOfReps);
    }
}
