package mk.ukim.finki.soa.workoutprogramsservice.web;

import mk.ukim.finki.soa.workoutprogramsservice.model.WorkoutProgram;
import mk.ukim.finki.soa.workoutprogramsservice.model.dto.WorkoutProgramDto;
import mk.ukim.finki.soa.workoutprogramsservice.service.WorkoutProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutProgramController {

    private final WorkoutProgramService workoutProgramService;

    public WorkoutProgramController(WorkoutProgramService workoutProgramService) {
        this.workoutProgramService = workoutProgramService;
    }

    @GetMapping("/all")
    public List<WorkoutProgram> getWorkoutPlans() {
        return this.workoutProgramService.findAll();
    }

    @GetMapping("/count")
    public Integer getNumberOfWorkoutPlans() {
        return this.workoutProgramService.getNumberOfWorkoutPlans();
    }

    @GetMapping("/{id}")
    public WorkoutProgram getWorkoutPlan(@PathVariable Long id) {
        return this.workoutProgramService.findById(id);
    }

    @PostMapping("/add")
    public WorkoutProgram addWorkoutPlan(@RequestBody WorkoutProgramDto workoutProgramDto) {
        return this.workoutProgramService.save(workoutProgramDto);
    }

    @PostMapping("/edit")
    public WorkoutProgram editWorkoutPlan(@RequestParam("workoutPlan") WorkoutProgram jsonWorkoutPlan) {
        return this.workoutProgramService.edit(jsonWorkoutPlan);
    }

    @PostMapping("/{id}/delete")
    public WorkoutProgram deleteWorkoutPlan(@PathVariable Long id) {
        return this.workoutProgramService.delete(id);
    }

    @PostMapping("/from-list")
    public List<WorkoutProgram> getWorkoutProgramsForOrder(@RequestBody List<Long> workoutProgramIds) {
        List<WorkoutProgram> workoutPrograms = new ArrayList<>();
        workoutProgramIds.forEach(id -> workoutPrograms.add(this.workoutProgramService.findById(id)));

        return workoutPrograms;
    }
}
