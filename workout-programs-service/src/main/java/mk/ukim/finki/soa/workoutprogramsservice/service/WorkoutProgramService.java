package mk.ukim.finki.soa.workoutprogramsservice.service;

import mk.ukim.finki.soa.workoutprogramsservice.model.WorkoutProgram;
import mk.ukim.finki.soa.workoutprogramsservice.model.dto.WorkoutProgramDto;

import java.util.List;

public interface WorkoutProgramService {
    Integer getNumberOfWorkoutPlans();

    List<WorkoutProgram> findAll();

    List<WorkoutProgram> findAllByCreator(String userEmail);

    WorkoutProgram findById(Long id);

    WorkoutProgram save(WorkoutProgramDto workoutProgramDto);

    WorkoutProgram edit(WorkoutProgram workoutPlan);

    WorkoutProgram delete(Long id);
}
