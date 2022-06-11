package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.exceptions.RobaEntityExistsRuntimeException;
import hr.java.restdatastock.exceptions.RobaEntityNotFoundRuntimeException;
import hr.java.restdatastock.model.entities.RobaEntity;
import hr.java.restdatastock.repositories.RobaRepository;
import hr.java.restdatastock.services.RobaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RobaServiceImpl implements RobaService {

    private final RobaRepository robaRepository;

    @Override
    public List<RobaEntity> getAll() {
        return this.robaRepository.findAll();
    }

    @Override
    public RobaEntity getOneById(final Long id) {
        return this.robaRepository.findById(id).orElseThrow(()-> new RobaEntityNotFoundRuntimeException(id));
    }

    @Override
    public RobaEntity createArticle(final RobaEntity roba) {
        return this.saveArticle(roba);
    }

    @Override
    public RobaEntity updateExistingArticle(final RobaEntity newArticleValue, final Long id) {
        return robaRepository.findById(id)
                .map(existingRoba -> {
                    existingRoba.setNazivArtikla(newArticleValue.getNazivArtikla());
                    existingRoba.setKolicina(newArticleValue.getKolicina());
                    existingRoba.setCijena(newArticleValue.getCijena());
                    existingRoba.setOpis(newArticleValue.getOpis());
                    existingRoba.setJmj(newArticleValue.getJmj());
                    return this.saveArticle(existingRoba);
                }).orElseThrow(() -> new RobaEntityExistsRuntimeException(newArticleValue));
    }

    @Override
    public HttpStatus deleteById(Long id) {
        final HttpStatus httpStatus;
        if (this.robaRepository.deleteRobaEntityById(id) > 0) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return httpStatus;
    }
//    @Override
//    public void deleteRoba(final Long id) {
//        this.robaRepository.deleteById(id);
//    }


    private RobaEntity saveArticle(RobaEntity robaEntity) {
        if (robaEntity.getId() != null) {
            List<RobaEntity> nazivArtiklaOverlap = robaRepository.checkIfExistsAllByNazivArtiklaAndIdNotEquals(robaEntity);
            if (!nazivArtiklaOverlap.isEmpty()) {
                throw new RobaEntityExistsRuntimeException(nazivArtiklaOverlap.get(0));
            }
        }
        return robaRepository.save(robaEntity);
    }
}
