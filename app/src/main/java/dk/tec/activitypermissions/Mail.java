package dk.tec.activitypermissions;

import java.io.Serializable;

public class Mail implements Serializable {

    private String Email;
    private String Subject;
    private String Content;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


}
