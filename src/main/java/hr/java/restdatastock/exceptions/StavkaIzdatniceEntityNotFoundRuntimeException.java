package hr.java.restdatastock.exceptions;

public class StavkaIzdatniceEntityNotFoundRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Could not find item shipment by this id: ";

    public StavkaIzdatniceEntityNotFoundRuntimeException(Long id) {
        super(String.format("%s: %d", ERROR_MSG, id));
    }
}
