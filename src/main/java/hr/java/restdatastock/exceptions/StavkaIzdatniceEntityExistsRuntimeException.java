package hr.java.restdatastock.exceptions;


import hr.java.restdatastock.models.entities.StavkaIzdatniceEntity;

public class StavkaIzdatniceEntityExistsRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Item shipment by thih 'Id' already exists: ";

    public StavkaIzdatniceEntityExistsRuntimeException(StavkaIzdatniceEntity stavkaIzdatnice) {
        super(String.format("%s: %s", ERROR_MSG, stavkaIzdatnice.getId()));
    }
}
