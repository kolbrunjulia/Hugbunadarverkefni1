package is.hi.hbv501g.hbv501g.Persistance.Repositories;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail  : sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Repository for the Exercise entity. Contains search methods
 *  as well as methods to save and delete an exercise.
 *****************************************************************************/

/**
 * The main repository for handling exercises.
 */

public interface ExerciseRepository extends JpaRepository<Exercise, Long > {
    Exercise save(Exercise exercise);

    void delete(Exercise exercise);

    List<Exercise> findAll();

    Exercise findByTitle(String title);

    List<Exercise> findByBodyPart(String body_part);

    List<Exercise> findByType(String type);
}
