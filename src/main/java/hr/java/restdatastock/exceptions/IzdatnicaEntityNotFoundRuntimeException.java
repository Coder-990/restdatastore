package hr.java.restdatastock.exceptions;

public class IzdatnicaEntityNotFoundRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Could not find shipment";

    public IzdatnicaEntityNotFoundRuntimeException(Long id) {
        super(String.format("%s: %d", ERROR_MSG, id));
    }
}
