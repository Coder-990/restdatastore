package hr.java.restdatastock.services;

import hr.java.restdatastock.models.entities.StavkaPrimkeEntity;

import java.util.List;

public interface StavkaPrimkeService {

    List<StavkaPrimkeEntity> getAll();

    StavkaPrimkeEntity getOneById(final Long id);
    StavkaPrimkeEntity createStavkaPrimke(final StavkaPrimkeEntity primka);

    StavkaPrimkeEntity createStornoStavkePrimke(final StavkaPrimkeEntity stavkaPrimke, final Long id);

}
