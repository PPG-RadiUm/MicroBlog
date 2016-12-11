package blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long fkUserId;
    private String content;
    private Calendar calendar;

    protected Message() {}

    public Message(Long fkUserId, String content, Calendar calendar) {
      this.fkUserId = fkUserId;
      this.content = content;
      this.calendar = calendar;
    }

    @Override
    public String toString() {
        return String.format(
                "Message[id=%d, fkUserId='%d', content='%s']",
                id, fkUserId, content);
    }

    public Long getId() {
        return id;
    }

    public Long getfkUserId() {
        return fkUserId;
    }

    public String getUserName() {
        return Application.userRepository.findById(this.fkUserId).getUserName();
    }

    public String getContent() {
        return content;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Message updateMessage(Long fkUserId, String content, Calendar calendar){
      this.fkUserId = fkUserId;
      this.content = content;
      this.calendar = calendar;
      return this;
    }
}
