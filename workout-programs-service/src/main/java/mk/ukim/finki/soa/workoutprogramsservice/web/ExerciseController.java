package mk.ukim.finki.soa.workoutprogramsservice.web;

import mk.ukim.finki.soa.workoutprogramsservice.model.Exercise;
import mk.ukim.finki.soa.workoutprogramsservice.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/count")
    public Integer getNumberOfExercises() {
        return this.exerciseService.getNumberOfExercises();
    }

    @GetMapping("/{id}")
    public Exercise getSingleExercise(@PathVariable Long id) {
        return this.exerciseService.findById(id);
    }

    @GetMapping("/all")
    public List<Exercise> getExercises() {
        return this.exerciseService.findAll();
    }

    @PostMapping("/add")
    public Exercise addExercise(@RequestBody Exercise exercise) {
        System.out.println(exercise);
        return this.exerciseService.save(exercise);
    }
}
