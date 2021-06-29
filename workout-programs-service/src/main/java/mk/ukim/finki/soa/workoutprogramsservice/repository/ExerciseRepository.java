package mk.ukim.finki.soa.workoutprogramsservice.repository;

import mk.ukim.finki.soa.workoutprogramsservice.model.Exercise;
import mk.ukim.finki.soa.workoutprogramsservice.model.enumeration.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findAllByMuscleGroup(MuscleGroup muscleGroup);

    List<Exercise> findAllByNameLike(String text);
}
