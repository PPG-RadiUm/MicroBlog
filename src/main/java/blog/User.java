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
    private String email;
    private String password;
    private String facebookId;
    private String twitterId;
    private String linkedInId;
    private String picture;

    public User() {

    }

    public User(String userName, String password, String facebookId, String twitterId, String linkedInId, String picture) {
        this.userName = userName;
        this.email = "example@example.com";
        this.password = password;
        this.facebookId = facebookId;
        this.twitterId = twitterId;
        this.linkedInId = linkedInId;
        this.picture = picture;
    }

    public User(String userName, String password, String email, String facebookId, String twitterId, String linkedInId, String picture) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.facebookId = facebookId;
        this.twitterId = twitterId;
        this.linkedInId = linkedInId;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, userName='%s', password='%s', facebookId='%s', twitterId='%s', linkedInId='%s']",
                id, userName, password, facebookId, twitterId, linkedInId);
    }

    public Long getId(){
        return id;
    }

    public String getUserName(){
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
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

    public void setId(Long id){
        this.id = id;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFacebookId(String facebookId){
        this.facebookId = facebookId;
    }

    public void setTwitterId(String twitterId){
        this.twitterId = twitterId;
    }

    public void setLinkedInId(String linkedInId){
        this.linkedInId = linkedInId;
    }

    public void setPicture(String picture){
        this.picture = picture;
    }

    public User updateUser(String userName, String password, String facebookId, String twitterId, String linkedInId, String picture){
        this.userName = userName;
        this.password = password;
        this.facebookId = facebookId;
        this.twitterId = twitterId;
        this.linkedInId = linkedInId;
        this.picture = picture;
        return this;
    }
}
