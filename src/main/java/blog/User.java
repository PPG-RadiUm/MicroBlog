package blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;
    private String facebookId;
    private String twitterId;
    private String linkedInId;
    private String picture;

    protected User() {}

    public User(String userName, String facebookId, String twitterId, String linkedInId, String picture) {
        this.userName = userName;
        this.facebookId = facebookId;
        this.twitterId = twitterId;
        this.linkedInId = linkedInId;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, userName='%s', facebookId='%s', twitterId='%s', linkedInId='%s']",
                id, userName, facebookId, twitterId, linkedInId);
    }

    public Long getId(){
        return id;
    }

    public String getUserName(){
        return userName;
    }

    public String getFacebookId(){
        return facebookId;
    }

    public String getTwitterId(){
        return twitterId;
    }

    public String getLinkedInId(){
        return linkedInId;
    }

    public String getPicture(){
        return picture;
    }

    public User updateUser(String userName, String facebookId, String twitterId, String linkedInId, String picture){
        this.userName = userName;
        this.facebookId = facebookId;
        this.twitterId = twitterId;
        this.linkedInId = linkedInId;
        this.picture = picture;
        return this;
    }
}
