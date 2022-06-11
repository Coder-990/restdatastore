package hr.java.restdatastock.services;

import hr.java.restdatastock.model.entities.RobaEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface RobaService {
    List<RobaEntity> getAll();

    RobaEntity getOneById(Long id);

    RobaEntity createArticle(final RobaEntity roba);

    RobaEntity updateExistingArticle(final RobaEntity roba, final Long id);

//    void deleteRoba(Long id);

    HttpStatus deleteById(Long id);
}
