package Model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private static final long serialVersionUID = 2L;
    private String message;
    private Date timeCreated;

    public Message(String message, Date timeCreated) {
        this.message = message;
        this.timeCreated = timeCreated;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
