package hr.java.restdatastock.services;

import hr.java.restdatastock.models.entities.IzdatnicaEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IzdatnicaService {

    List<IzdatnicaEntity> getAll();

    IzdatnicaEntity getOneById(final Long id);

    IzdatnicaEntity createIzdatnica(final IzdatnicaEntity izdatnica);

    HttpStatus deleteById(final Long id);

//    void deleteIzdatnica(final Long id);
}
