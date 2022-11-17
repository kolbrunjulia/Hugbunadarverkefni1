package is.hi.hbv501g.hbv501g.Services;
import is.hi.hbv501g.hbv501g.Persistance.Entities.ExerciseCombo;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Service for the ExerciseCombo entity. Contains search methods
 *  as well as methods to save and delete exerciseCombo.
 *****************************************************************************/

/**
 * Interface definitions for the ExerciseComboService
 *
 * Details for the individual implementation can be seen in the /Implementation
 * directory.
 */
public interface ExerciseComboService {

    /**
     * Standard method to find an exerciseCombo by title.
     *
     * @param title the title of the exerciseCombo to be found
     * @return the specific exerciseCombo if it exists
     */
    ExerciseCombo findByExercise(String title);

    /**
     * Standard method to find all exerciseCombos that exist.
     *
     * @return a list of all the exerciseCombos.
     */
    List<ExerciseCombo> findAll();

    /**
     * Standard method to find an exerciseCombo by ID.
     *
     * @param ID the ID of the exerciseCombo that is to be found
     * @return the exerciseCombo corresponding to the ID if it exists
     */
    ExerciseCombo findByID(long ID);

    /**
     * Standard method to save an exerciseCombo.
     *
     * @param exerciseCombo the exerciseCombo that is to be saved
     * @return the primary key of the exerciseCombo
     */
    ExerciseCombo save(ExerciseCombo exerciseCombo);

    /**
     * Standard method to delete an exerciseCombo.
     *
     * @param exerciseCombo the exerciseCombo that is to be deleted
     */
    void delete(ExerciseCombo exerciseCombo);

    /**
     * Standard method that finds exerciseCombos by workout.
     *
     * @param workout the workout of the exerciseCombo to be found
     * @return a list of exerciseCombos that are contained in the specific workout if it exists
     */
    List<ExerciseCombo> findByWorkout(Workout workout);

}
