package name.ealen.domain.vo;


/**
 * @author EalenXie on 2021/4/30 9:09
 */
public class Resp {
    private String message;

    public Resp() {
    }

    public Resp(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
