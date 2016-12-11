package blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Calendar;

@Controller
public class GreetingController {

    @GetMapping("/index")
    public String indexPage(Model model) {
        // On récupère les messages à afficher
        model.addAttribute("messageRepository", Application.messageRepository.findAll());
        model.addAttribute("message", new MessageForm());

        return "index";
    }

    @PostMapping("/index")
    public String indexPage(@ModelAttribute MessageForm messageForm, Model model) {
        // On enregistre le message envoyé
        Application.messageRepository.save(new Message(new Long(1), messageForm.getContent(), Calendar.getInstance()));

        // On récupère les messages à afficher
        model.addAttribute("messageRepository", Application.messageRepository.findAll());
        model.addAttribute("message", new MessageForm()); 

        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
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
