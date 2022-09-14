package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.exceptions.RobaEntityExistsRuntimeException;
import hr.java.restdatastock.exceptions.RobaEntityNotFoundRuntimeException;
import hr.java.restdatastock.models.entities.RobaEntity;
import hr.java.restdatastock.repositories.RobaRepository;
import hr.java.restdatastock.services.RobaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
    public RobaEntity updateExistingArticle(final RobaEntity roba, final Long id) {
        return robaRepository.findById(id)
                .map(existingRoba -> {
                    existingRoba.setNazivArtikla(roba.getNazivArtikla());
                    existingRoba.setCijena(roba.getCijena());
                    existingRoba.setKolicina(roba.getKolicina());
                    existingRoba.setJmj(roba.getJmj());
                    existingRoba.setOpis(roba.getOpis());
                    return this.saveArticle(existingRoba);
                }).orElseThrow(() -> new RobaEntityExistsRuntimeException(roba));
    }

    @Override
    public HttpStatus deleteById(Long id) {
        final HttpStatus httpStatus;
        if (this.robaRepository.deleteRobaEntityById(id) > 0) {
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            httpStatus = HttpStatus.UNAUTHORIZED;
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
