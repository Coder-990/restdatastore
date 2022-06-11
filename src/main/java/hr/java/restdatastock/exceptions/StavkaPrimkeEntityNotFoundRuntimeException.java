package hr.java.restdatastock.exceptions;

public class StavkaPrimkeEntityNotFoundRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Could not find item recipient by this id: ";

    public StavkaPrimkeEntityNotFoundRuntimeException(Long id) {
        super(String.format("%s: %d", ERROR_MSG, id));
    }
}
