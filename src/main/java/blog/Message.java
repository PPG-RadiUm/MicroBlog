package blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long fkUserId;
    private String content;
    private Calendar calendar;
    private ArrayList<String> hashtags;

    private static final Logger log = LoggerFactory.getLogger(Message.class);

    protected Message() {}

    public Message(Long fkUserId, String content, Calendar calendar) {
        this.fkUserId = fkUserId;
        this.content = content;
        this.calendar = calendar;
        this.hashtags = new ArrayList<String>();
        setHashtags();
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

    public String getAvatar() {
        return Application.userRepository.findById(this.fkUserId).getPicture();
    }

    public String getContent() {
        return content;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public ArrayList<String> getHashtags() {
        return hashtags;
    }

    public boolean gotHashtags() {
        if(hashtags.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String getPostedTime() {
        Calendar now = Calendar.getInstance();
        Long timeInMillis = now.getTimeInMillis() - calendar.getTimeInMillis();
        return convertTimeInMillis(timeInMillis);
    }

    public String convertTimeInMillis(Long timeInMillis) {
        int seconds = (int) (timeInMillis / 1000) % 60 ;
        int minutes = (int) ((timeInMillis / (1000*60)) % 60);
        int hours   = (int) ((timeInMillis / (1000*60*60)) % 24);

        if(hours != 0) {
            return hours + " hour" + ((hours > 1) ? "s" : "") + " ago";
        } else if (minutes != 0) {
            return minutes + " minute" + ((minutes > 1) ? "s" : "") + " ago";
        } else if (seconds > 10) {
            return "Less than one minute";
        } else {
            return "Now";
        }
    }

    public void setHashtags() {
        int index = -1;
        String myString = content;
        boolean stop = (content.indexOf("#") == -1) ? false : true;
        while(stop){
            index = myString.indexOf("#") + 1;
            myString = myString.substring(index);
            if(myString.indexOf(" ") != -1) {
                hashtags.add(myString.substring(0, myString.indexOf(" ")));
            } else {
                hashtags.add(myString.substring(0));
                stop = false;
            }
        }
    }

    public Message updateMessage(Long fkUserId, String content, Calendar calendar){
        this.fkUserId = fkUserId;
        this.content = content;
        this.calendar = calendar;
        setHashtags();
        return this;
    }
}
