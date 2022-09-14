package hr.java.restdatastock.services;

import hr.java.restdatastock.models.entities.PrimkaEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PrimkaService {

    List<PrimkaEntity> getAll();

    PrimkaEntity getOneById(final Long id);

    PrimkaEntity createPrimka(final PrimkaEntity primka);

    HttpStatus deleteById(final Long id);

//    void deletePrimka(final Long id);
}
