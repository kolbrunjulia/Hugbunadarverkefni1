package is.hi.hbv501g.hbv501g.Services.Implementation;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import is.hi.hbv501g.hbv501g.Persistance.Repositories.WorkoutRepository;
import is.hi.hbv501g.hbv501g.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Implementation of the WorkoutService. Contains search methods
 *  as well as containing methods that delete and save workouts. Also contains
 *  a methods that lists all workouts. The class communicates to the
 *  WorkoutRepository and calls the methods that are contained in
 *  WorkoutRepository.
 *****************************************************************************/

/**
 * The main service used by routes that handles workouts. It pulls information
 * from the WorkoutRepository.
 */

@Service
public class WorkoutServiceImplementation implements WorkoutService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutServiceImplementation(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    /**
     * Basic find one service.
     *
     * @param title the title of the workout to find
     * @return the workout corresponding to the title if it exists
     */
    @Override
    public Workout findByTitle(String title) {
        // Only returns the first workout that matches the title
        return workoutRepository.findByTitle(title).get(0);
    }

    /**
     * Basic find all service.
     *
     * @return a list of all the workouts
     */
    @Override
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    /**
     * Service that finds the workouts made by admin.
     *
     * @return the list of all the workouts made by admin
     */
    @Override
    public List<Workout> findWorkoutsByMadeByAdminIsTrue() {
        return workoutRepository.findWorkoutsByMadeByAdminIsTrue();
    }

    /**
     * Basic find one service.
     *
     * @param ID the id of the workout to find
     * @return the workout containing the ID to be found if it exists
     */
    @Override
    public Workout findByID(long ID) {
        return workoutRepository.findByID(ID);
    }

    /**
     * Basic service to save a workout.
     *
     * @param workout the workout to save
     * @return the primary key of the entity
     */
    @Override
    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    /**
     * Basic service to delete a workout.
     * @param workout the workout to delete
     */
    @Override
    public void delete(Workout workout) {
        workoutRepository.delete(workout);
    }

    /**
     * Service that lists all workouts by specific duration value.
     *
     * @param keyword the keyword the user puts in while searching for a workout
     * @param duration the workouts that contain the desired duration
     * @return a list of workouts that match the keyword and/or the desired duration
     */
    @Override
    public List<Workout> listAll(String keyword, String duration) {
            if (keyword != null) {
                return workoutRepository.search(keyword);
            }
            if(duration != null) {
                if (duration.equals("0")) {
                    return workoutRepository.searchWorkoutByDurationIsLessThanEqualAndDurationGreaterThanEqualAndMadeByAdminIsTrue(6000, 0);
                }

                if (duration.equals("1")) {
                    return workoutRepository.searchWorkoutByDurationIsLessThanEqualAndDurationGreaterThanEqualAndMadeByAdminIsTrue(20, 0);
                }
                if (duration.equals("2")) {
                    return workoutRepository.searchWorkoutByDurationIsLessThanEqualAndDurationGreaterThanEqualAndMadeByAdminIsTrue(40, 20);
                }
                if (duration.equals("3")) {
                    return workoutRepository.searchWorkoutByDurationIsLessThanEqualAndDurationGreaterThanEqualAndMadeByAdminIsTrue(60, 40);
                }
                if (duration.equals("4")) {
                    return workoutRepository.searchWorkoutByDurationIsLessThanEqualAndDurationGreaterThanEqualAndMadeByAdminIsTrue(6000, 60);
                }
            }
            return workoutRepository.findWorkoutsByMadeByAdminIsTrue();

    }

    /**
     * Service that adds the user to a workout.
     *
     * @param user the current user
     * @param workout workout that the user is added to
     */
    @Override
    public void addUserToWorkout(User user,Workout workout) {
        List<User> userList = workout.getUser();
        workout.getUser().add(user);
    }
}
