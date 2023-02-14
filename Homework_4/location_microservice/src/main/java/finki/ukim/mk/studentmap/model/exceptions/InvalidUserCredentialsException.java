package finki.ukim.mk.studentmap.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {

    public InvalidUserCredentialsException() {
        super(String.format("Invalid user credentials"));
    }
}
