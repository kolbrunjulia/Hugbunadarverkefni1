package is.hi.hbv501g.hbv501g.Services;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.List;

/******************************************************************************
 *  Name    : Hópur 7
 *  E-mail  : sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Service for the User entity. Contains search methods as well as
 *  methods to save and delete exerciseCombo. Also contains a method that
 *  indicates whether a certain user is logged in to the system or not.
 *****************************************************************************/

/**
 * Interface definitions for the UserService
 *
 * Details for the individual implementation can be seen in the /Implementation
 * directory.
 */

@Service
public interface UserService {

    /**
     * Standard method to save a user.
     *
     * @param user the user that is to be saved
     * @return the primary key of the entity
     */
    User save(User user);

    /**
     * Standard method to delete a user.
     *
     * @param user the user that is to be deleted
     */
    void delete(User user);

    /**
     * Standard method to find all users.
     *
     * @return a list of all users
     */
    List<User> findAll();

    /**
     * Standard method to login user
     *
     * @param user the user to be logged in
     * @return success response for a username and password match
     */
    User login(User user);

    /**
     * Standard method to find user by username.
     *
     * @param username the username of the user to be found
     * @return the user corresponding to the username if it exists
     */
    User findByUsername(String username);

    /**
     * Standard method to find user by ID.
     *
     * @param id the ID of the user to be found
     * @return the user corresponding to the ID if it exists
     */
    User findByID(long id);

    /**
     * Method that adds a workout to the current user
     *
     * @param workout workout that is added to the user
     * @param user the current user
     */
    void addWorkoutToUser(Workout workout, User user);

    /**
     * Boolean method that checks if a user is logged in.
     *
     * @param session the current session
     * @return true if user is logged in, else false
     */
    public Boolean userLoggedIn(HttpSession session);



}


