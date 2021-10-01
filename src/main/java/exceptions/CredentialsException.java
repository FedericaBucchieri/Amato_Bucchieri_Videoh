package exceptions;

public class CredentialsException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;

    public CredentialsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
