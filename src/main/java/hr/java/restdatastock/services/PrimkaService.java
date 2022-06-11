package hr.java.restdatastock.services;

import hr.java.restdatastock.model.entities.PrimkaEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PrimkaService {

    List<PrimkaEntity> getAll();

    PrimkaEntity getOneById(Long id);

    PrimkaEntity createPrimka(final PrimkaEntity primka);

    HttpStatus deleteById(Long id);

//    void deletePrimka(final Long id);
}
