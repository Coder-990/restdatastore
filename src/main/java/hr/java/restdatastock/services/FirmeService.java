package hr.java.restdatastock.services;

import hr.java.restdatastock.models.entities.FirmeEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface FirmeService {

    List<FirmeEntity> getAll();

    FirmeEntity getOneById(final Long id);

    FirmeEntity createFirma(final FirmeEntity company);

    FirmeEntity updateExistingFirma(final FirmeEntity company, final Long id);

    HttpStatus deleteById(final Long id);

}
