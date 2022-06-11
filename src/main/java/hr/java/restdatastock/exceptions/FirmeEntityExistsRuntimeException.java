package hr.java.restdatastock.exceptions;


import hr.java.restdatastock.model.entities.FirmeEntity;

public class FirmeEntityExistsRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Company by thih 'OIB' already exists: ";

    public FirmeEntityExistsRuntimeException(FirmeEntity firmeEntity) {
        super(String.format("%s: %s", ERROR_MSG, firmeEntity.getOibFirme()));
    }
}
