package finki.ukim.mk.studentmap.model.exceptions;

public class InvalidArgumentsException extends RuntimeException{

    public InvalidArgumentsException(){
        super(String.format("Invalid arguments"));
    }
}
