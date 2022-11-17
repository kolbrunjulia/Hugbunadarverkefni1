package is.hi.hbv501g.hbv501g.Services;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import java.util.List;

/******************************************************************************
 *  Name   : Group 7
 *  E-mail : sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Service for the Workout entity. Contains search methods as well as
 *  methods to save and delete a workout. Also contains a method that lists all
 *  the workouts that exist.
 *****************************************************************************/

/**
 * Interface definitions for the WorkoutService
 *
 * Details for the individual implementation can be seen in the /Implementation
 * directory.
 */
public interface WorkoutService {

        /**
         * Standard method to find one Workout by its name
         *
         * @param title the title of the workout to find
         * @return the workout that corresponds to that name if it exists
         */
        Workout findByTitle(String title);

        /**
         * Standard method to find the complete list of Workouts.
         *
         * @return the list of all workouts
         */
        List<Workout> findAll();

        /**
         * Method that finds all the workouts made by admin.
         *
         * @return the list of workouts made by the admin
         */
        List<Workout> findWorkoutsByMadeByAdminIsTrue();

        /**
         * Standard method to find one workout by its name
         *
         * @param ID the id of the workout to find
         * @return the workout that corresponds to the ID if it exists
         */
        Workout findByID(long ID);

        /**
         * Standard method to save a workout.
         *
         * @param workout the workout to save
         * @return the primary key of the entity
         */
        Workout save(Workout workout);

        /**
         * Standard method to delete a workout.
         *
         * @param workout the workout to delete
         */
        void delete(Workout workout);

        /**
         * Method that lists all workouts by keyword from search bar or duration.
         *
         * @param keyword the keyword the user puts in while searching for a workout
         * @param duration the duration of each workout
         * @return the list of the workouts that match the keyword or the preferred duration
         */
        List<Workout> listAll(String keyword, String duration);

        /**
         * Method that adds the current user to a workout
         *
         * @param user the current user
         * @param workout workout that the user is added to
         */
        void addUserToWorkout(User user, Workout workout);
}
