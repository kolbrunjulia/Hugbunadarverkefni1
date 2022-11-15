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
 *  Nafn    : Hópur 7
 *  T-póstur: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Lýsing  : Controller fyrir Workout klasann. Controllerinn inniheldur aðferðir
 *  sem lista upp öll workout sem eru í boði, auk þess að bjóða notandanum upp á
 *  að búa til sína eigin æfingu og bæta henni við listann. Notandi getur einnig
 *  eytt workouts og opnað hverja workout til að sjá hvað hún inniheldur.
 *
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
    public String homePage(Model model, @Param("keyword") String keyword, HttpSession session){
        if(userService.userLoggedIn(session)) {
            // Call a method in a service class
            List<Workout> allWorkouts = workoutService.listAll(keyword, userService.findByID(1));
            User loggedInUser = (User) session.getAttribute("LoggedInUser");
            // Add some data to the model
            model.addAttribute("workouts", allWorkouts);
            model.addAttribute("keyword", keyword);
            model.addAttribute("LoggedInUser", loggedInUser);
            return "home";
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/addWorkout",method = RequestMethod.GET)
    public String addWorkoutForm(Workout workout, HttpSession session){
        if(userService.userLoggedIn(session)) {
            return "addWorkout";
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/addWorkout",method = RequestMethod.POST)
    public String addWorkout(Workout workout, BindingResult result,Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            if (result.hasErrors()) {
                return "addWorkout";
            }
            User userToAddWorkoutTo = (User) session.getAttribute("LoggedInUser");
            userToAddWorkoutTo.getMyWorkouts().add(workout);
            workoutService.save(workout);
            userService.save(userToAddWorkoutTo);
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


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public String deleteWorkout(@PathVariable("id") long id,  Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            Workout workoutToDelete = workoutService.findByID(id);
            workoutService.delete(workoutToDelete);
            return "redirect:/workouts";
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/workout/{id}",method = RequestMethod.GET)
    public String openWorkoutForm(@PathVariable("id") long id,Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            Workout workoutToOpen = workoutService.findByID(id);
            List<ExerciseCombo> exerciseCombos = exerciseComboService.findByWorkout(workoutToOpen);
            //List<ExerciseCombo> exerciseCombosToOpen = exerciseComboService.findAll();
            model.addAttribute("workout", workoutToOpen);
            model.addAttribute("ExerciseCombos", exerciseCombos);

            return "workout";
        }
        return "redirect:/";
    }
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




    @RequestMapping(value ="/myWorkouts",method = RequestMethod.POST)
    public String myWorkoutsForm(Model model/*, @Param("keyword") String keyword, */,HttpSession session){
        if(userService.userLoggedIn(session)) {
            // Call a method in a service class
            //List<Workout> myWorkouts = workoutService.listAll(keyword);
            User userToAddWorkoutTo = (User) session.getAttribute("LoggedInUser");
            List <Workout> workoutsToDisplay = userToAddWorkoutTo.getMyWorkouts();
            model.addAttribute( "workoutsToDisplay", workoutsToDisplay);

            // Add some data to the model
            // model.addAttribute("workouts", workoutsToDisplay);
            //model.addAttribute("keyword", keyword);
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
            return "myWorkouts";
        }
        return "redirect:/";
    }

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
            } else return "redirect:/workoutTil";
        }
        return "redirect:/error_page1";
    }
    //workoutService.addUserToWorkout(userToAddWorkoutTo, workoutToAddUserTo);
    //userServiceImplementation.addWorkoutToUser(workoutToAddUserTo,userToAddWorkoutTo);
    //model.addAttribute("myWorkouts", myWorkouts);



}