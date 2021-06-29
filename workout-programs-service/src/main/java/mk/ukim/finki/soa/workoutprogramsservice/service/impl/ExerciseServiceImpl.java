package mk.ukim.finki.soa.workoutprogramsservice.service.impl;

import mk.ukim.finki.soa.workoutprogramsservice.model.Exercise;
import mk.ukim.finki.soa.workoutprogramsservice.repository.ExerciseRepository;
import mk.ukim.finki.soa.workoutprogramsservice.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Integer getNumberOfExercises() {
        return this.exerciseRepository.findAll().size();
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public List<Exercise> searchByName(String text) {
        return this.exerciseRepository.findAllByNameLike("%" + text + "%");
    }

    @Override
    public Exercise findById(Long id) {
        return this.exerciseRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Exercise save(Exercise exercise) {

        return exerciseRepository.save(exercise);
    }
}
