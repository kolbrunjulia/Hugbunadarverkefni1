package is.hi.hbv501g.hbv501g.Controllers;
import is.hi.hbv501g.hbv501g.Persistance.Entities.ExerciseCombo;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import is.hi.hbv501g.hbv501g.Services.ExerciseComboService;
import is.hi.hbv501g.hbv501g.Services.UserService;
import is.hi.hbv501g.hbv501g.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail  : sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Controller for the Workout class. The Controller contains
 *  a method that lists all the workouts as well as a method that allows the
 *  user to open a specific workout, adding a workout to the user's my workouts,
 *  editing a my workout and adding exercises to the user's my workouts.
 *  Every single method makes sure that a user is logged in
 *  before it is performed. If a user is not logged in then the user is
 *  redirected to the login page.
 *****************************************************************************/

@Controller
public class WorkoutController {
    private WorkoutService workoutService;
    private ExerciseComboService exerciseComboService;
    private UserService userService;
    @Autowired
    public WorkoutController(WorkoutService workoutService, ExerciseComboService exerciseComboService, UserService userService){
        this.workoutService = workoutService;
        this.exerciseComboService = exerciseComboService;
        this.userService = userService;
    }

    @RequestMapping("/workouts")
    public String homePage(Model model, @Param("keyword") String keyword, @Param("duration") String duration, HttpSession session){
        if(userService.userLoggedIn(session)) {
            // Call a method in a service class
            List<Workout> allWorkouts = workoutService.listAll(keyword,duration);
            User loggedInUser = (User) session.getAttribute("LoggedInUser");
            // Add some data to the model
            model.addAttribute("workouts", allWorkouts);
            model.addAttribute("duration",duration);
            model.addAttribute("keyword", keyword);
            model.addAttribute("LoggedInUser", loggedInUser);
            return "home";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/addWorkoutAdmin",method = RequestMethod.GET)
    public String addWorkoutFormAdmin(Workout workout, HttpSession session){
        if(userService.userLoggedIn(session)) {
            return "addWorkout";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/addWorkoutAdmin",method = RequestMethod.POST)
    public String addWorkoutAdmin(Workout workout, BindingResult result,Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            if (result.hasErrors()) {
                return "addWorkout";
            }
            workoutService.save(workout);
            return "redirect:/workouts";
        }
        return "redirect:/";
    }



    /**
     * GET on /addWorkout
     *
     * @param workout the workout to be added
     * @return addWorkout template if user is logged in, otherwise login page
     */
    @RequestMapping(value = "/addWorkout",method = RequestMethod.GET)
    public String addWorkoutForm(Workout workout, HttpSession session, Model model){
        if(userService.userLoggedIn(session)) {
            User user = (User) session.getAttribute("LoggedInUser");
            model.addAttribute("LoggedInUser", user);
            return "addWorkout";
        }
        return "redirect:/";
    }

    /**
     * POST of /addWorkout
     *
     * @param workout the workout to be added
     * @param result result
     * @param model model
     * @param session the current session
     * @return /myWorkouts if successful
     */
    @RequestMapping(value = "/addWorkout",method = RequestMethod.POST)
    public String addWorkout(Workout workout, BindingResult result,Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            if (result.hasErrors()) {
                return "addWorkout";
            }
            User user = (User) session.getAttribute("LoggedInUser");
            workout.getUser().add(user);
            workoutService.save(workout);

            user.getMyWorkouts().add(workout);
            userService.save(user);
            return "redirect:/myWorkouts";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/workoutTil")
    public String workoutTil(HttpSession session){
        if(userService.userLoggedIn(session)) {
            return "workoutTil";
        }
        return "redirect:/error_page1";
    }


    /**
     * GET on /delete/{id}
     *
     * @param id the id of the workout to be deleted
     * @param model model
     * @param session current session
     * @return /wokrouts if user is logged in, else return login page
     */
    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public String deleteWorkout(@PathVariable("id") long id,  Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            Workout workoutToDelete = workoutService.findByID(id);
            workoutService.delete(workoutToDelete);
            return "redirect:/workouts";
        }
        return "redirect:/";
    }

    /**
     * GET on /workout/{id}
     *
     * @param id the id of the workout
     * @param model model
     * @param session current session
     * @return workout template if user is logged in, else login page
     */
    @RequestMapping(value = "/workout/{id}",method = RequestMethod.GET)
    public String openWorkoutForm(@PathVariable("id") long id,Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            Workout workoutToOpen = workoutService.findByID(id);
            List<ExerciseCombo> exerciseCombos = exerciseComboService.findByWorkout(workoutToOpen);
            //List<ExerciseCombo> exerciseCombosToOpen = exerciseComboService.findAll();
            model.addAttribute("workout", workoutToOpen);
            model.addAttribute("ExerciseCombos", exerciseCombos);
            User user = (User) session.getAttribute("LoggedInUser");
            model.addAttribute("LoggedInUser", user);
            return "workout";
        }
        return "redirect:/";
    }


    /**
     * GET on /deleteMyWorkout/{id}
     *
     * @param id the ID of the workout that is to be deleted
     * @param model model
     * @param session current session
     * @return /myWorkouts if user is logged in
     */
    @RequestMapping(value="/deleteMyWorkout/{id}",method = RequestMethod.GET)
    public String deleteMyWorkout(@PathVariable("id") long id,  Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            User userToDeleteFromWorkout = (User) session.getAttribute("LoggedInUser");
            Workout workoutToDeleteFromUser = workoutService.findByID(id);

            for(Workout w:userToDeleteFromWorkout.getMyWorkouts()){
                if(w.getID()==workoutToDeleteFromUser.getID()){
                    userToDeleteFromWorkout.getMyWorkouts().remove(userToDeleteFromWorkout.getMyWorkouts().indexOf(w));
                    break;
                }
            }

            userToDeleteFromWorkout.setMyWorkouts((userToDeleteFromWorkout.getMyWorkouts()));

            for(User u:workoutToDeleteFromUser.getUser()){
                if(u.getID()==userToDeleteFromWorkout.getID()){
                    workoutToDeleteFromUser.getUser().remove(workoutToDeleteFromUser.getUser().indexOf(u));
                    break;
                }
            }

            workoutToDeleteFromUser.setUser(workoutToDeleteFromUser.getUser());
            workoutService.save(workoutToDeleteFromUser);
            userService.save(userToDeleteFromWorkout);

            return "redirect:/myWorkouts";
        }
        return "redirect:/error_page1";
    }

    /**
     * POST on /myWorkouts
     *
     * @param model model
     * @param session current session
     * @return /myWorkouts if user is in session
     */
    @RequestMapping(value ="/myWorkouts",method = RequestMethod.POST)
    public String myWorkoutsForm(Model model/*, @Param("keyword") String keyword, */,HttpSession session){
        if(userService.userLoggedIn(session)) {
            User userToAddWorkoutTo = (User) session.getAttribute("LoggedInUser");
            List <Workout> workoutsToDisplay = userToAddWorkoutTo.getMyWorkouts();
            model.addAttribute( "workoutsToDisplay", workoutsToDisplay);
            model.addAttribute("LoggedInUser", userToAddWorkoutTo);

            return "myWorkouts";
        }
        return "redirect:/";
    }

    @RequestMapping(value ="/myWorkouts")
    public String myWorkout(Model model/*, @Param("keyword") String keyword, */,HttpSession session){
        if(userService.userLoggedIn(session)) {
            // Call a method in a service class
            //List<Workout> myWorkouts = workoutService.listAll(keyword);
            User userToAddWorkoutTo = (User) session.getAttribute("LoggedInUser");
            List <Workout> workoutsToDisplay = userToAddWorkoutTo.getMyWorkouts();
            model.addAttribute( "workoutsToDisplay", workoutsToDisplay);
            model.addAttribute("LoggedInUser", userToAddWorkoutTo);
            return "myWorkouts";
        }
        return "redirect:/";
    }

    /**
     * GET on /addToMyWorkouts/{id}
     *
     * @param id the ID of the workout that is to be added to my workouts
     * @param model model
     * @param session current session
     * @return /workouts
     */
    @RequestMapping(value="/addToMyWorkouts/{id}", method = RequestMethod.GET)
    public String addToMyWorkouts(@PathVariable("id") long id,  Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            User userToAddWorkoutTo = (User) session.getAttribute("LoggedInUser");
            Workout workoutToAddUserTo = workoutService.findByID(id);

            List<Long> wIDs = new ArrayList<>();

            boolean workoutDoesExist = false; //gerum fyrst ráð fyrir að workoutið sé ekki nú þegar í MyWorkouts
            for (Workout w : userToAddWorkoutTo.getMyWorkouts()) {
                wIDs.add(w.getID());
            }
            if (wIDs.contains(workoutToAddUserTo.getID())) workoutDoesExist = true;

            if (!workoutDoesExist) {
                userToAddWorkoutTo.getMyWorkouts().add(workoutToAddUserTo);
                userToAddWorkoutTo.setMyWorkouts(userToAddWorkoutTo.getMyWorkouts());

                workoutToAddUserTo.getUser().add(userToAddWorkoutTo);
                workoutToAddUserTo.setUser(workoutToAddUserTo.getUser());
                workoutService.save(workoutToAddUserTo);
                userService.save(userToAddWorkoutTo);
                return "redirect:/workouts";
            }
            else return "redirect:/workoutTil";
        }
        return "redirect:/error_page1";
    }
}