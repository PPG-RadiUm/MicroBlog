package blog;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/blog")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    /*@MessageMapping("/blog")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        //request.getSession().setAttribute("testVariable", "Test Values!!");
        //Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }*/
}
