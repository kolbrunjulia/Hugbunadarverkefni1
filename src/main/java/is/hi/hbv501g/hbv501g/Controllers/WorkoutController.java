package is.hi.hbv501g.hbv501g.Controllers;
import is.hi.hbv501g.hbv501g.Persistance.Entities.ExerciseCombo;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import is.hi.hbv501g.hbv501g.Services.ExerciseComboService;
import is.hi.hbv501g.hbv501g.Services.ExerciseService;
import is.hi.hbv501g.hbv501g.Services.Implementation.UserServiceImplementation;
import is.hi.hbv501g.hbv501g.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private UserServiceImplementation userServiceImplementation;
    @Autowired
    public WorkoutController(WorkoutService workoutService, ExerciseComboService exerciseComboService, UserServiceImplementation userServiceImplementation){
        this.workoutService = workoutService;
        this.exerciseComboService = exerciseComboService;
        this.userServiceImplementation = userServiceImplementation;
    }

    @RequestMapping("/workouts")
    public String homePage(Model model, @Param("keyword") String keyword, HttpSession session){
        if(userServiceImplementation.userLoggedIn(session)) {
            // Call a method in a service class
            List<Workout> allWorkouts = workoutService.listAll(keyword);
            // Add some data to the model
            model.addAttribute("workouts", allWorkouts);
            model.addAttribute("keyword", keyword);
            return "home";
        }
        return "redirect:/error_page1";
    }
    @RequestMapping(value = "/addWorkout",method = RequestMethod.GET)
    public String addWorkoutForm(Workout workout, HttpSession session){
        if(userServiceImplementation.userLoggedIn(session)) {
            return "addWorkout";
        }
        return "redirect:/error_page1";
    }

    @RequestMapping(value = "/addWorkout",method = RequestMethod.POST)
    public String addWorkout(Workout workout, BindingResult result,Model model, HttpSession session){
        if(userServiceImplementation.userLoggedIn(session)) {
            if (result.hasErrors()) {
                return "addWorkout";
            }
            workoutService.save(workout);
            return "redirect:/workouts";
        }
        return "redirect:/error_page1";
    }
    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public String deleteWorkout(@PathVariable("id") long id,  Model model, HttpSession session){
        if(userServiceImplementation.userLoggedIn(session)) {
            Workout workoutToDelete = workoutService.findByID(id);
            workoutService.delete(workoutToDelete);
            return "redirect:/workouts";
        }
        return "redirect:/error_page1";
    }
    @RequestMapping(value = "/workout/{id}",method = RequestMethod.GET)
    public String openWorkoutForm(@PathVariable("id") long id,Model model, HttpSession session){
        if(userServiceImplementation.userLoggedIn(session)) {
            Workout workoutToOpen = workoutService.findByID(id);
            List<ExerciseCombo> exerciseCombos = exerciseComboService.findByWorkout(workoutToOpen);
            //List<ExerciseCombo> exerciseCombosToOpen = exerciseComboService.findAll();
            model.addAttribute("workout", workoutToOpen);
            model.addAttribute("ExerciseCombos", exerciseCombos);

            return "workout";
        }
        return "redirect:/error_page1";
    }

    @RequestMapping("/myWorkouts")
    public String myWorkouts(Model model, @Param("keyword") String keyword, HttpSession session){
        if(userServiceImplementation.userLoggedIn(session)) {
            // Call a method in a service class
            List<Workout> myWorkouts = workoutService.listAll(keyword);
            // Add some data to the model
            model.addAttribute("workouts", myWorkouts);
            model.addAttribute("keyword", keyword);
            return "myWorkouts";
        }
        return "redirect:/error_page1";
    }


}