package is.hi.hbv501g.hbv501g.Services.Implementation;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import is.hi.hbv501g.hbv501g.Persistance.Repositories.UserRepository;
import is.hi.hbv501g.hbv501g.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.List;

/******************************************************************************
 *  Name   : Group 7
 *  E-mail : sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description: Implementation of the UserService. Contains search methods
 *  as well as methods that save and delete a user. The class communicates
 *  to the UserRepository and calls the methods that are contained in
 *  the UserRepository class. Also contains a method that checks if a user
 *  is logged in as well as a method that makes sure that the user already
 *  exists before it is logged in.
 *****************************************************************************/

/**
 * The main service used by routes that handles users. It pulls information
 * from the UserRepository.
 */

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Basic save service.
     *
     * @param user the user that is to be saved
     * @return the primary key of the entity
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Basic delete service.
     *
     * @param user the user that is to be deleted
     */
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


    /**
     * Basic find all service.
     *
     * @return a list of all the users
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Basic find by username service.
     *
     * @param username the username of the user to be found
     * @return the user corresponding to the specific username if it exists
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Basic Boolean service that check if the user is logged in
     *
     * @param session HttpSession
     * @return true if user is logged in,  else false
     *
     */
    @Override
    public Boolean userLoggedIn(HttpSession session) {
        if(session.getAttribute("LoggedInUser") != null) {
            return true;
        }
        return false;
    }

    /**
     * Service that checks if user exists and whether the password is correct
     *
     * @param user current user
     * @return doesExist which us the current user if the user exists and
     * the password is correct. Otherwise returns null.
     */
    @Override
    public User login(User user) {
         User doesExist = findByUsername(user.getUsername());
        if(doesExist != null){
            if(doesExist.getPassword().equals(user.getPassword())){
                return doesExist;
            }
        }
        return null;
    }

    /**
     * Basic find by ID service.
     *
     * @param id the ID of the user to be found
     * @return the user corresponding to the ID if it exists
     */
    @Override
    public User findByID(long id){
        return userRepository.findByID(id);
    }

    /**
     * Service that adds a workout to the current user.
     *
     * @param user the current user
     * @param workout workout that is added to the current user
     */
    @Override
    public void addWorkoutToUser(Workout workout, User user ) {
        user.getMyWorkouts().add(workout);

    }




}


