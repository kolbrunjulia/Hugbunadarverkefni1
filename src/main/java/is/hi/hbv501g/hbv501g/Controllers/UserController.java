package is.hi.hbv501g.hbv501g.Controllers;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Controller for the User class. The class contains methods
 *  that allow the user to create a new account and to log in to its own account.
 *****************************************************************************/

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * GET on /signup
     *
     * @param user the user that is signing up
     * @return template signup_page
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user) {
        return "signup_page";
    }

    /**
     * POST on /signup
     *
     * @param user the user that is signing up
     * @param result result
     * @param model model
     * @param session current session
     * @return /workouts if user does not exist
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()) {
            return "redirect:/signup_page";
        }
        User exists = userService.findByUsername(user.getUsername());
        if(exists == null){
            userService.save(user);
            session.setAttribute("LoggedInUser", user);
            model.addAttribute("LoggedInUser",user);
        }
        return "redirect:/workouts";
    }

    /**
     * GET on /
     *
     * @param user the user that is logging in
     * @return the template login_page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login_page";
    }

    /**
     * POST on /
     *
     * @param user the user that is logging in
     * @param result result
     * @param model model
     * @param session current session
     * @return /workouts if user exists
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "error_page1";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            System.out.println(user);
            return "redirect:/workouts";
        }
        return "redirect:/error_page1";
    }


    /**
     * GET on /loggedin
     *
     * @param session current session
     * @param model model
     * @return the template LoggedInUser
     */
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser != null) {
            model.addAttribute("LoggedInUser", sessionUser);
            // ATH breytti þessu - mögulega eitthvað skrítið
            return "LoggedInUser";
        }
        return "redirect:/";
    }

    /**
     * POST on /logout
     *
     * @param session current session
     * @param model model
     * @return / wheen session has been invalidated
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session, Model model){
        session.invalidate();
        System.out.println("You are successfully logged out!");
        return "redirect:/";
    }

    /**
     * POST on /settings
     *
     * @param session current session
     * @param model model
     * @return the template settings
     */
    @RequestMapping(value = "/settings",method = RequestMethod.POST)
    public String userSettings(HttpSession session,Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if (sessionUser != null) {
            model.addAttribute("LoggedInUser", sessionUser);
            return "settings";
        }
        return "redirect:/";
    }
}
