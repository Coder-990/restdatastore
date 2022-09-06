package hr.java.restdatastock.services;

import hr.java.restdatastock.model.entities.StavkaPrimkeEntity;

import java.util.List;

public interface StavkaPrimkeService {

    List<StavkaPrimkeEntity> getAll();

    StavkaPrimkeEntity getOneById(Long id);
    StavkaPrimkeEntity createStavkaPrimke(final StavkaPrimkeEntity primka);

    StavkaPrimkeEntity createStornoStavkePrimke(final StavkaPrimkeEntity stavkaPrimke, final Long id);

}
