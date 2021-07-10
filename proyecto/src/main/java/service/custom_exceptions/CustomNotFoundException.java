package service.custom_exceptions;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException(String exception) {
        super(exception);
    }
}