package mk.ukim.finki.soa.workoutprogramsservice.service;

import mk.ukim.finki.soa.workoutprogramsservice.model.Exercise;

import java.util.List;

public interface ExerciseService {
    Integer getNumberOfExercises();

    List<Exercise> findAll();

    List<Exercise> searchByName(String text);

    Exercise findById(Long id);

    Exercise save(Exercise exercise);
}
