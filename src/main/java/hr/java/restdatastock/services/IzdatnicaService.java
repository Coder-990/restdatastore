package hr.java.restdatastock.services;

import hr.java.restdatastock.model.entities.IzdatnicaEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IzdatnicaService {

    List<IzdatnicaEntity> getAll();

    IzdatnicaEntity getOneById(Long id);

    IzdatnicaEntity createIzdatnica(final IzdatnicaEntity izdatnica);

    HttpStatus deleteById(Long id);

//    void deleteIzdatnica(final Long id);
}
