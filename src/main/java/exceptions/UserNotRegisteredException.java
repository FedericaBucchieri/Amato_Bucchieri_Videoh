package exceptions;

public class UserNotRegisteredException extends Exception{
    private static final long serialVersionUID = 1L;
    private String message;

    public UserNotRegisteredException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
