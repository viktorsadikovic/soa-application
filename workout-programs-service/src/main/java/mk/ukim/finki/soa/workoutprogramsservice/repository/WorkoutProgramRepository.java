package mk.ukim.finki.soa.workoutprogramsservice.repository;

import mk.ukim.finki.soa.workoutprogramsservice.model.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram, Long> {
    List<WorkoutProgram> findAllByCreator(String creator);
}
