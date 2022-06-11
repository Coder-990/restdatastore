package hr.java.restdatastock.exceptions;

public class FirmeEntityNotFoundRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Could not find company by this id: ";
    public FirmeEntityNotFoundRuntimeException(long id) {
        super(String.format("%s: %d", ERROR_MSG, id));
    }
}
