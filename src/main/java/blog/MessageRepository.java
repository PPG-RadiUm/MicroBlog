package blog;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import java.util.Calendar;

public interface MessageRepository extends CrudRepository<Message, Long> {

    Message findById(Long id);
    List<Message> findByFkUserId(Long fkUserId);
    List<Message> findByContent(String content);
    List<Message> findByCalendar(Calendar calendar);
}
