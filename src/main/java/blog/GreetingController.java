package blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Calendar;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    // On va sur la page index
    @GetMapping("/index")
    public String indexPage(Model model) {
        // On récupère les messages à afficher
        model.addAttribute("messageRepository", Application.messageRepository.findAllByOrderByIdDesc());
        model.addAttribute("messageForm", new MessageForm());
        model.addAttribute("userIdSession", Application.userIdSession);

        return "index";
    }

    // Quand on a cliqué sur le bouton "Say it" pour envoyer un message Ou si un utilisateur a tenté de se connecter depuis la page login
    @PostMapping("/index")
    public String indexPage(@ModelAttribute MessageForm messageForm, @ModelAttribute LoginForm loginForm, Model model) {

        if(messageForm.getContent() != null && messageForm.getContent() != ""){
          // On enregistre le message envoyé
          Application.messageRepository.save(new Message(Application.userIdSession, messageForm.getContent(), Calendar.getInstance()));
        }

        if(loginForm.getUserName() != null && loginForm.getPassword() != null){
          User user = Application.userRepository.findByUserNameAndPassword(loginForm.getUserName(), loginForm.getPassword());

          // Si on trouve un utilisateur avec le nom donné et le mot de passe donné
          if(user != null){
            // On met en session l'id de l'utilisateur connecté
            Application.userIdSession = new Long(user.getId());
          } else {
            return "redirect:login?loginFailed=true";
          }
        }

        // On récupère les messages à afficher
        model.addAttribute("messageRepository", Application.messageRepository.findAllByOrderByIdDesc());
        model.addAttribute("messageForm", new MessageForm());
        model.addAttribute("userIdSession", Application.userIdSession);

        // Si on est connecté, on ajouté un attribut qui correspond au nom d'utilisateur
        if(Application.userIdSession != null){
          model.addAttribute("userUserName", Application.userRepository.findById(Application.userIdSession).getUserName());
        }

        return "index";
    }

    // On va sur la page login
    @GetMapping("/login")
    public String loginPage(@RequestParam(value="loginFailed",required=false) String loginFailed, Model model) {
        // Si l'utilisateur est déjà connecté
        if(Application.userIdSession != null){
          // On le redirige vers la page index
          return "redirect:index";
        }

        // Si la connexion a échoué : erreur à propos du nom d'utilisateur ou mot de passe
        if(loginFailed != null){
          model.addAttribute("loginFailed", true);
        }
        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    // On veut se deconnecter
    @GetMapping("/logout")
    public String logoutAction(Model model) {
        Application.userIdSession = null;

        return "redirect:login";
    }

    // On veut editer son profil
    @GetMapping("/edit_profile")
    public String editProfilePage(Model model) {
        if(Application.userIdSession == null){
          // On le redirige vers la page index
          return "redirect:index";
        }

        model.addAttribute("user", Application.userRepository.findById(Application.userIdSession));
        model.addAttribute("newUser", new User());

        return "edit_profile";
    }

    // On veut editer son profil
    @PostMapping("/edit_profile")
    public String editProfilePage(@ModelAttribute User newUser, Model model) {
        if(Application.userIdSession == null){
          // On le redirige vers la page index
          return "redirect:index";
        }

        System.out.println(newUser.getUserName());
        System.out.println(newUser.getPassword());
        System.out.println(newUser.getFacebookId());
        System.out.println(newUser.getTwitterId());

        // L'utilisateur a appuyé sur le bouton "Save" pour modifier son profil
        if(newUser != null && newUser.getUserName() != null && newUser.getUserName() != ""){
            User cTemp = Application.userRepository.findById(Application.userIdSession);
            //Application.userRepository.save(cTemp.updateUser(newUser.getUserName(), newUser.getPassword(), newUser.getFacebookId(), newUser.getTwitterId(), newUser.getLinkedInId(), newUser.getPicture()));
        }

        model.addAttribute("user", Application.userRepository.findById(Application.userIdSession));
        model.addAttribute("newUser", new User());

        return "edit_profile";
    }

    /*@MessageMapping("/blog")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getContent() + "!");
    }

    @GetMapping("/index")
    public String indexPage(Model model){
      model.addAttribute("index", new Greeting());
      return "index";
    }

    @MessageMapping("/blog")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        //request.getSession().setAttribute("testVariable", "Test Values!!");
        //Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }*/
}
