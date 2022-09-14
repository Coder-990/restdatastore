package hr.java.restdatastock.exceptions;


import hr.java.restdatastock.models.entities.StavkaPrimkeEntity;

public class StavkaPrimkeEntityExistsRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Item recipient by this 'Id' already exists: ";

    public StavkaPrimkeEntityExistsRuntimeException(StavkaPrimkeEntity stavkaPrimke) {
        super(String.format("%s: %s", ERROR_MSG, stavkaPrimke.getId()));
    }
}
