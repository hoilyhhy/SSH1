package hhy.entity;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class BaseEntity {
    private Date handledatetime;
    private String handleusername;

    public BaseEntity() {
    }

    public Date getHandledatetime() {
        return handledatetime;
    }

    public void setHandledatetime(Date handledatetime) {
        this.handledatetime = handledatetime;
    }

    public String getHandleusername() {
        return handleusername;
    }

    public void setHandleusername(String handleusername) {
        this.handleusername = handleusername;
    }
}
