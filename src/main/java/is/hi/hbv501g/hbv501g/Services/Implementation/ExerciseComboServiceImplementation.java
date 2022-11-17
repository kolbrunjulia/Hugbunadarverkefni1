package is.hi.hbv501g.hbv501g.Services.Implementation;
import is.hi.hbv501g.hbv501g.Persistance.Entities.ExerciseCombo;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import is.hi.hbv501g.hbv501g.Persistance.Repositories.ExerciseComboRepository;
import is.hi.hbv501g.hbv501g.Persistance.Repositories.ExerciseRepository;
import is.hi.hbv501g.hbv501g.Persistance.Repositories.WorkoutRepository;
import is.hi.hbv501g.hbv501g.Services.ExerciseComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description: Implementation of the ExerciseComboService. Contains search
 *  methods as well as methods that save and delete an exerciseCombo. The claa
 *  communicates to ExerciseComboRepository and calls the methods that are there.
 *****************************************************************************/

/**
 * The main service used by routes that handles exerciseCombos. It pulls information
 * from the ExerciseComboRepository.
 */

@Service
public class ExerciseComboServiceImplementation implements ExerciseComboService {

    private ExerciseComboRepository exerciseComboRepository;
    private ExerciseRepository exerciseRepository;

    private WorkoutRepository workoutRepository;

    @Autowired
    public ExerciseComboServiceImplementation(ExerciseComboRepository exerciseComboRepository) {
        this.exerciseComboRepository = exerciseComboRepository;
    }

    /**
     * Basic find one service.
     *
     * @param title the title of the exerciseCombo to be found
     * @return the exerciseCombo that contains the title if it exists
     */
    @Override
    public ExerciseCombo findByExercise(String title) {
        return exerciseComboRepository.findByExercise(exerciseRepository.findByTitle(title));
    }

    /**
     * Basic find all service.
     *
     * @return a list of all the exerciseCombos
     */
    @Override
    public List<ExerciseCombo> findAll() {
        return exerciseComboRepository.findAll();
    }

    /**
     * Basic find one service.
     *
     * @param ID the ID of the exerciseCombo that is to be found
     * @return the exerciseCombo corresponding to the ID if it exists
     */
    @Override
    public ExerciseCombo findByID(long ID) {
        return exerciseComboRepository.findByID(ID);
    }

    /**
     * Basic save service.
     *
     * @param exerciseCombo the exerciseCombo that is to be saved
     * @return the primary key of the entity
     */
    // TODO: 17.10.2022 Bæta við ordering gæa
    @Override
    public ExerciseCombo save(ExerciseCombo exerciseCombo) {
        // Er þetta rétt hugsun?
       //exerciseCombo.setWorkout(workoutRepository.findByID(ID));
       return exerciseComboRepository.save(exerciseCombo);
    }

    /**
     * Basic delete service
     *
     * @param exerciseCombo the exerciseCombo that is to be deleted
     */
    @Override
    public void delete(ExerciseCombo exerciseCombo) {
        exerciseComboRepository.delete(exerciseCombo);
    }

    /**
     * Basic find by service.
     *
     * @param workout the workout that contains the exerciseCombos
     * @return the list of exerciseCombos for the corresponding workout
     */
    @Override
    public List<ExerciseCombo> findByWorkout(Workout workout) {
        return exerciseComboRepository.findByWorkout(workout);
    }
}
