package mk.ukim.finki.soa.workoutprogramsservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.soa.workoutprogramsservice.model.dto.WorkoutProgramDto;
import mk.ukim.finki.soa.workoutprogramsservice.model.enumeration.BodyPart;
import mk.ukim.finki.soa.workoutprogramsservice.model.enumeration.WorkoutType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "workout_programs")
public class WorkoutProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creator;

    @Column(length = 1000)
    private String title;

    @Column(length = 8000)
    private String description;

    private String image;

    private LocalDateTime submissionTime;

    @Enumerated(value = EnumType.STRING)
    private WorkoutType workoutType;

    private Boolean equipment;

    @Enumerated(value = EnumType.STRING)
    private BodyPart bodyPart;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<ExerciseWrapper> exercises;

    private Double price = 0.0;

    public WorkoutProgram(String creator, String title, String description, String image, LocalDateTime submissionTime,
                          WorkoutType workoutType, Boolean equipment, BodyPart bodyPart,
                          List<ExerciseWrapper> exercises, Double price) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.image = image;
        this.submissionTime = submissionTime;
        this.workoutType = workoutType;
        this.equipment = equipment;
        this.bodyPart = bodyPart;
        this.exercises = exercises;
        this.price = price;
    }

    public static WorkoutProgram create(WorkoutProgramDto workoutProgramDto, List<ExerciseWrapper> exercises) {
        return new WorkoutProgram(workoutProgramDto.getCreator(), workoutProgramDto.getTitle(), workoutProgramDto.getDescription(),
                                 workoutProgramDto.getImage(), LocalDateTime.now(), workoutProgramDto.getWorkoutType(),
                                 workoutProgramDto.getEquipment(), workoutProgramDto.getBodyPart(), exercises,
                                 workoutProgramDto.getPrice());
    }
}
