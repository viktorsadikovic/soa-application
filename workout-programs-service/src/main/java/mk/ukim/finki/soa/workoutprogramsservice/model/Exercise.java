package mk.ukim.finki.soa.workoutprogramsservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.soa.workoutprogramsservice.model.enumeration.MuscleGroup;
import mk.ukim.finki.soa.workoutprogramsservice.model.enumeration.WorkoutType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private MuscleGroup muscleGroup;

    @Enumerated(value = EnumType.STRING)
    private WorkoutType workoutType;

    private Boolean equipment;

    public Exercise(String name, MuscleGroup muscleGroup, Boolean equipment) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.equipment = equipment;
    }
}
