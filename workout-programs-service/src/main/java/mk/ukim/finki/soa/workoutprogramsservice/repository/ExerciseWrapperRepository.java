package mk.ukim.finki.soa.workoutprogramsservice.repository;

import mk.ukim.finki.soa.workoutprogramsservice.model.ExerciseWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseWrapperRepository extends JpaRepository<ExerciseWrapper, Long> {
}
