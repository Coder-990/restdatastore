package hr.java.restdatastock.services;

import hr.java.restdatastock.model.entities.StavkaIzdatniceEntity;

import java.util.List;

public interface StavkaIzdatniceService {

    List<StavkaIzdatniceEntity> getAll();

    StavkaIzdatniceEntity getOneById(Long id);
    StavkaIzdatniceEntity createStavkaIzdatnice(final StavkaIzdatniceEntity izdatnica);

    StavkaIzdatniceEntity createStornoStavkeIzdatnice(final StavkaIzdatniceEntity updateStavke);

}
