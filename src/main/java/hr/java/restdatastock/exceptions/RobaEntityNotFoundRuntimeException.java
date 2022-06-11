package hr.java.restdatastock.exceptions;

public class RobaEntityNotFoundRuntimeException extends RuntimeException{
    public static final String ERROR_MSG = "Could not find article by this id: ";

    public RobaEntityNotFoundRuntimeException(long id) {
        super(String.format("%s: %d", ERROR_MSG, id));
    }
}
