package blog;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(Long id);
    User findByUserName(String userName);
    User findByUserNameAndPassword(String userName, String password);
    List<User> findByFacebookId(String facebookId);
    List<User> findByTwitterId(String twitterId);
    List<User> findByLinkedInId(String linkedInId);
}
