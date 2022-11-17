package is.hi.hbv501g.hbv501g.Services;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Exercise;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Service for the Exercise entity. Contains search methods as
 *  well as containing methods to save and delete an exerciseCombo.
 *****************************************************************************/

/**
 * Interface definitions for the ExerciseService
 *
 * Details for the individual implementation can be seen in the /Implementation
 * directory.
 */
public interface ExerciseService {

    /**
     * Standard method to save an exercise.
     *
     * @param exercise the exercise that is to be saved
     * @return the primary key of the exercise
     */
    Exercise save(Exercise exercise);

    /**
     * Standard method to delete an exercise.
     *
     * @param exercise the exercise that is to be deleted
     */
    void delete(Exercise exercise);

    /**
     * Standard method to find all exercises.
     *
     * @return a list of all exercises
     */
    List<Exercise> findAll();

    /**
     * Standard method to find an exercise by title.
     *
     * @param title the title of the exercise to be found
     * @return the exercise that contains that title if it exists
     */
    Exercise findByTitle(String title);

    /**
     * Standard method that finds exercise by body part.
     *
     * @param body_part the body part of the exercise to be found
     * @return a list of exercises containing the specific body part if it exists
     */
    List<Exercise> findByBodyPart(String body_part);

    /**
     * Standard method that finds exercise by type.
     *
     * @param type the type of the exercise to be found
     * @return a list of exercises containing the specific type if it exists
     */
    List<Exercise> findByType(String type);

    /**
     * Standard method that lists all exercises.
     *
     * @return a list of all the exercises
     */
    List<Exercise> listAll();

}
