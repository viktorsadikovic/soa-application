package mk.ukim.finki.soa.workoutprogramsservice.service.impl;

import mk.ukim.finki.soa.workoutprogramsservice.model.Exercise;
import mk.ukim.finki.soa.workoutprogramsservice.model.ExerciseWrapper;
import mk.ukim.finki.soa.workoutprogramsservice.model.WorkoutProgram;
import mk.ukim.finki.soa.workoutprogramsservice.model.dto.WorkoutProgramDto;
import mk.ukim.finki.soa.workoutprogramsservice.repository.ExerciseWrapperRepository;
import mk.ukim.finki.soa.workoutprogramsservice.repository.WorkoutProgramRepository;
import mk.ukim.finki.soa.workoutprogramsservice.service.ExerciseService;
import mk.ukim.finki.soa.workoutprogramsservice.service.WorkoutProgramService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutProgramServiceImpl implements WorkoutProgramService {

    private final WorkoutProgramRepository workoutProgramRepository;
    private final ExerciseService exerciseService;
    private final ExerciseWrapperRepository exerciseWrapperRepository;

    public WorkoutProgramServiceImpl(WorkoutProgramRepository workoutProgramRepository, ExerciseService exerciseService, ExerciseWrapperRepository exerciseWrapperRepository) {
        this.workoutProgramRepository = workoutProgramRepository;
        this.exerciseService = exerciseService;
        this.exerciseWrapperRepository = exerciseWrapperRepository;
    }

    @Override
    public Integer getNumberOfWorkoutPlans() {
        return this.findAll().size();
    }

    @Override
    public List<WorkoutProgram> findAll() {
        return this.workoutProgramRepository.findAll();
    }

    @Override
    public List<WorkoutProgram> findAllByCreator(String userEmail) {
        return this.workoutProgramRepository.findAllByCreator(userEmail);
    }

    @Override
    public WorkoutProgram findById(Long id) {
        return this.workoutProgramRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public WorkoutProgram save(WorkoutProgramDto workoutProgramDto) {
        List<ExerciseWrapper> exerciseWrapperList = new ArrayList<>();

        workoutProgramDto.getExerciseWrappers().forEach(exerciseWrapperDto -> {
            Exercise exercise = this.exerciseService.findById(exerciseWrapperDto.getExerciseId());
            ExerciseWrapper exerciseWrapper = ExerciseWrapper.create(exercise,exerciseWrapperDto.getNumberOfSets(),
                                                                     exerciseWrapperDto.getNumberOfReps());

            exerciseWrapperRepository.save(exerciseWrapper);
            exerciseWrapperList.add(exerciseWrapper);
        });

        WorkoutProgram workoutProgram = WorkoutProgram.create(workoutProgramDto, exerciseWrapperList);

        return workoutProgramRepository.save(workoutProgram);
    }

    @Override
    public WorkoutProgram edit(WorkoutProgram workoutProgram) {
        WorkoutProgram existingWorkoutProgram = this.findById(workoutProgram.getId());

        existingWorkoutProgram.setTitle(workoutProgram.getTitle());
        existingWorkoutProgram.setDescription((workoutProgram.getDescription()));
        existingWorkoutProgram.setWorkoutType(workoutProgram.getWorkoutType());
        existingWorkoutProgram.setEquipment((workoutProgram.getEquipment()));
        existingWorkoutProgram.setBodyPart(workoutProgram.getBodyPart());
        existingWorkoutProgram.setExercises(workoutProgram.getExercises());
        existingWorkoutProgram.setPrice(workoutProgram.getPrice());

        return this.workoutProgramRepository.save(existingWorkoutProgram);
    }

    @Override
    public WorkoutProgram delete(Long id) {
        WorkoutProgram workoutProgram = this.findById(id);

        workoutProgramRepository.delete(workoutProgram);

        return workoutProgram;
    }
}
