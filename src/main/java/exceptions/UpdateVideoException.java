package exceptions;

public class UpdateVideoException extends Exception{
    private static final long serialVersionUID = 1L;
    private String message;

    public UpdateVideoException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
