package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.exceptions.FirmeEntityExistsRuntimeException;
import hr.java.restdatastock.exceptions.FirmeEntityNotFoundRuntimeException;
import hr.java.restdatastock.model.dtos.FirmeDto;
import hr.java.restdatastock.model.entities.FirmeEntity;
import hr.java.restdatastock.repositories.FirmeRepository;
import hr.java.restdatastock.services.FirmeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class FirmeServiceImpl implements FirmeService {

    private final FirmeRepository firmeRepository;

    @Override
    public List<FirmeEntity> getAll() {
        return this.firmeRepository.findAll();
    }

    @Override
    public FirmeEntity getOneById(final Long id) {
        return this.firmeRepository.findById(id).orElseThrow(() -> new FirmeEntityNotFoundRuntimeException(id));
    }

    @Override
    public FirmeEntity createFirma(final FirmeEntity firma) {
        return this.saveFirma(firma);
    }

    @Override
    public FirmeEntity updateExistingFirma(final FirmeEntity newFirmaValue, final Long id) {
        return Optional.of(this.getOneById(id))
                .map(existingFirma -> {
                    existingFirma.setOibFirme(newFirmaValue.getOibFirme());
                    existingFirma.setNazivFirme(newFirmaValue.getNazivFirme());
                    return this.saveFirma(existingFirma);
                }).orElseThrow(() -> new FirmeEntityExistsRuntimeException(newFirmaValue));
    }

    @Override
    public HttpStatus deleteById(final Long id) {
        final HttpStatus httpStatus;
        if (this.firmeRepository.deleteFirmeEntityById(id) > 0){
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return httpStatus;
    }

//    @Override
//    public void deleteFirma(Long id) {
//        this.firmeRepository.deleteById(id);
//    }

    private FirmeEntity saveFirma(final FirmeEntity firmeEntity) {
        if (firmeEntity.getId() != null) {
            List<FirmeEntity> firmeOibOverlap = firmeRepository.checkIfExistsAllByOibAndIdNotEquals(firmeEntity);
            if (!firmeOibOverlap.isEmpty()) {
                throw new FirmeEntityExistsRuntimeException(firmeOibOverlap.get(0));
            }
        }
        return firmeRepository.save(firmeEntity);
    }



}
