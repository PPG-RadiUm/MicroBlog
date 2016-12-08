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

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public Iterable<User> getUserByUserName(@RequestParam(required=false) String userName) {
        if(userName != null){
            return repository.findByUserName(userName);
        }else{
            return repository.findAll();
        }
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public User saveUser(@RequestBody User c){
        return repository.save(new User(c.getUserName(), c.getFacebookId(), c.getTwitterId(), c.getLinkedInId(), c.getPicture()));
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User c, @PathVariable("id") Long id) {
        User cTemp = repository.findOne(id);
        return repository.save(cTemp.updateUser(c.getUserName(), c.getFacebookId(), c.getTwitterId(), c.getLinkedInId(), c.getPicture()));
    }
}
