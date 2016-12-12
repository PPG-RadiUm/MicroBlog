package blog;

import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Date;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageRepository repository;

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public Message getMessageById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public Iterable<Message> getMessageByMessageContent(@RequestParam(required=false) String content) {
        if(content != null){
            return repository.findByContent(content);
        }else{
            return repository.findAllByOrderByIdDesc();
        }
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Message saveMessage(@RequestBody Message c){
        return repository.save(new Message(c.getfkUserId(), c.getContent(), c.getCalendar()));
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public Message updateMessage(@RequestBody Message c, @PathVariable("id") Long id) {
        Message cTemp = repository.findOne(id);
        return repository.save(cTemp.updateMessage(c.getfkUserId(), c.getContent(), c.getCalendar()));
    }
}
