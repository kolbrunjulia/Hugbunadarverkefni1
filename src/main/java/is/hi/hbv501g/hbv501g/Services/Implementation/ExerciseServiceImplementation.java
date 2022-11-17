package is.hi.hbv501g.hbv501g.Services.Implementation;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Exercise;
import is.hi.hbv501g.hbv501g.Persistance.Repositories.ExerciseRepository;
import is.hi.hbv501g.hbv501g.Services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description: Implementation of the ExerciseService. Contains search methods
 *  as well as methods that save and delete ExerciseCombo. The class
 *  communicates to ExerciseRepository and calls the methods there.
 *****************************************************************************/

/**
 * The main service used by routes that handles exercises. It pulls information
 * from the ExerciseRepository.
 */

@Service
public class ExerciseServiceImplementation implements ExerciseService {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImplementation(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    /**
     * Basic save service.
     *
     * @param exercise the exercise that is to be saved
     * @return the primary key of the entity
     */
    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    /**
     * Basic delete service.
     *
     * @param exercise the exercise that is to be deleted
     */
    @Override
    public void delete(Exercise exercise) {
        exerciseRepository.delete(exercise);
    }

    /**
     * Basic service to find all.
     *
     * @return a list of all exercises
     */
    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    /**
     * Basic find by service.
     *
     * @param title the title of the exercise to be found
     * @return the exercise that contains the title if it exists
     */
    @Override
    public Exercise findByTitle(String title) {
        return exerciseRepository.findByTitle(title);
    }

    /**
     * Basic find one service.
     *
     * @param body_part the body part of the exercise to be found
     * @return a list of exercises that contain the specific body part
     */
    @Override
    public List<Exercise> findByBodyPart(String body_part) {
        return exerciseRepository.findByBodyPart(body_part);
    }

    /**
     * Basic find one service.
     *
     * @param type the type of the exercise to be found
     * @return a list of exercises that contain the type
     */
    @Override
    public List<Exercise> findByType(String type) {
        return exerciseRepository.findByType(type);
    }


    /**
     * Basic list all service.
     *
     * @return null
     */
    @Override
    public List<Exercise> listAll() {
        return null;
    }

}
