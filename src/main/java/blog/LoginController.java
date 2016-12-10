package blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/login")
    public String getLoginPage() {
        log.info("Coucou");
        return "login";

    }

    @RequestMapping(value = "/login/user/connect", method=RequestMethod.POST)
    public String getUserByUserNameAndPassword(@ModelAttribute User user) {
        log.info("Coucou");
        User usr = repository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if(usr != null){
            // model.addAttribute("username", user.getUserName());
            // model.addAttribute("user", user.getPassword());
            return "index";
        }else{
            return "login";
        }

    }
}
