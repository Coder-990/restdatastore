package hr.java.restdatastock.dialogutil;

import hr.java.restdatastock.model.entities.RacunEntity;

public interface DialogService {

    void getWarningAlert(String alert);

    boolean isCredentialsValid();

    boolean isEntityUnableToRemove();

    boolean isEntityRemoved();

    boolean isDataPickedFromTableViewAlert();

    boolean isUserRegistered(RacunEntity racun);
}
