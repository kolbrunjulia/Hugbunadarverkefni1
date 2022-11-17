package is.hi.hbv501g.hbv501g.Persistance.Repositories;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Exercise;
import is.hi.hbv501g.hbv501g.Persistance.Entities.ExerciseCombo;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail  : sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Repository for the ExerciseCombo entity. Contains search methods
 *  as well as methods to save and delete an exerciseCombo.
 *****************************************************************************/

/**
 * The main repository for handling exerciseCombo data.
 */

public interface ExerciseComboRepository extends JpaRepository<ExerciseCombo, Long > {
    ExerciseCombo save(ExerciseCombo exerciseCombo);

    void delete(ExerciseCombo exerciseCombo);

    List<ExerciseCombo> findAll();

    ExerciseCombo findByID(long ID);

    ExerciseCombo findByExercise(Exercise exercise);

    List<ExerciseCombo> findByWorkout(Workout workout);

    List<ExerciseCombo> findExerciseComboByWorkout(Workout workout);
}

