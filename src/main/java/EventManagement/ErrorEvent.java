package EventManagement;

public class ErrorEvent implements Event{
    private String message;

    public ErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
