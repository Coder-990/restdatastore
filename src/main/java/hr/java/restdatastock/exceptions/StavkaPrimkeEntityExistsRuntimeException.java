package hr.java.restdatastock.exceptions;


import hr.java.restdatastock.model.entities.StavkaPrimkeEntity;

public class StavkaPrimkeEntityExistsRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Item recipient by thih 'Id' already exists: ";

    public StavkaPrimkeEntityExistsRuntimeException(StavkaPrimkeEntity stavkaPrimke) {
        super(String.format("%s: %s", ERROR_MSG, stavkaPrimke.getId()));
    }
}
