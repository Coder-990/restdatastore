package hr.java.restdatastock.exceptions;

public class PrimkaEntityNotFoundRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Could not find recipient by this id: ";

    public PrimkaEntityNotFoundRuntimeException(Long id) {
        super(String.format("%s: %d", ERROR_MSG, id));
    }
}
