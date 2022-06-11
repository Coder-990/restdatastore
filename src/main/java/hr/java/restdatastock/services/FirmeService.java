package hr.java.restdatastock.services;

import hr.java.restdatastock.model.entities.FirmeEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface FirmeService {

    List<FirmeEntity> getAll();

    FirmeEntity getOneById(Long id);

    FirmeEntity createFirma(final FirmeEntity company);

    FirmeEntity updateExistingFirma(final FirmeEntity company, final Long id);

//    void deleteFirma(final Long id);

    HttpStatus deleteById(Long id);

}
