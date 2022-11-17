package is.hi.hbv501g.hbv501g.Controllers;
import is.hi.hbv501g.hbv501g.Persistance.Entities.ExerciseCombo;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import is.hi.hbv501g.hbv501g.Services.ExerciseComboService;
import is.hi.hbv501g.hbv501g.Services.ExerciseService;
import is.hi.hbv501g.hbv501g.Services.UserService;
import is.hi.hbv501g.hbv501g.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Controller for the Exercise class. The class communicates
 *  to the following services: ExerciseComboService, WorkoutService and
 *  ExerciseService. The controller contains methods that add an exercise and
 *  delete exercises. Every single method makes sure that a user is logged in
 *  before it is performed. If a user is not logged in then the user is
 *  redirected to the login page.
 *****************************************************************************/

@Controller
public class ExerciseController {
    private ExerciseComboService exerciseComboService;
    private WorkoutService workoutService;
    private ExerciseService exerciseService;
    private UserService userService;

    @Autowired
    public ExerciseController(ExerciseComboService exerciseComboService, WorkoutService workoutService, ExerciseService exerciseService, UserService userService) {
        this.exerciseComboService = exerciseComboService;
        this.exerciseService = exerciseService;
        this.workoutService = workoutService;
        this.userService = userService;
    }

    /**
     * GET on /deleteExercise/{workout_id}/{id}
     *
     * @param exerciseCombo the exerciseCombo that is to be deleted
     * @param id id of the exerciseCombo that is to be deleted
     * @param workout_id id of the workout we are deletin the exerciseCombo from
     * @param model model
     * @param session current session
     * @return /workout/{workout_id}
     */
    @RequestMapping(value = "deleteExercise/{workout_id}/{id}", method = RequestMethod.GET)
    public String deleteExercise(ExerciseCombo exerciseCombo, @PathVariable("id") long id, @PathVariable("workout_id") long workout_id, Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            ExerciseCombo exerciseComboToDelete = exerciseComboService.findByID(id);
            exerciseComboService.delete(exerciseComboToDelete);
            return "redirect:/workout/{workout_id}";
        }
        return "redirect:/";
    }

    /**
     * GET on /workout/{id}/addExercise
     *
     * @param exerciseCombo exerciseCombo that is to be added
     * @param id ID of the workout we want to add the exerciseCombo to
     * @param model model
     * @param session current session
     * @return the template addExerciseCombo
     */
    @RequestMapping(value = "workout/{id}/addExercise", method = RequestMethod.GET)
    public String addExerciseComboForm(ExerciseCombo exerciseCombo,@PathVariable("id") long id,Model model, HttpSession session){
        if(userService.userLoggedIn(session)) {
            Workout workoutToOpen = workoutService.findByID(id);
            model.addAttribute("workout", workoutToOpen);
            return "addExerciseCombo";
        }
        return "redirect:/";
    }

    /**
     * POST on /workout/{id}/addExercise
     *
     * @param exerciseCombo the exerciseCombo to be added
     * @param workout_id ID of the workout we want to add the exerciseCombo to
     * @param exercise_title title of the exerciseCombo
     * @param reps number of repititions
     * @param sets number of sets
     * @param kg kilograms
     * @param session current session
     * @return /workout/{id}
     */
    @RequestMapping(value = "/workout/{id}/addExercise", method = RequestMethod.POST)
    public String addExerciseCombo(ExerciseCombo exerciseCombo,@PathVariable("id") long workout_id, String exercise_title, int reps, int sets, double kg, HttpSession session){
        if(userService.userLoggedIn(session)) {
            exerciseCombo.setExercise(exerciseService.findByTitle(exercise_title));
            exerciseCombo.setWorkout(workoutService.findByID(workout_id));
            exerciseComboService.save(exerciseCombo);
            return "redirect:/workout/{id}";
        }
        return "redirect:/";
    }
}