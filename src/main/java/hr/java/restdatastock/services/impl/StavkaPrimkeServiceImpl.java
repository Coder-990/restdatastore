package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.exceptions.StavkaPrimkeEntityExistsRuntimeException;
import hr.java.restdatastock.exceptions.StavkaPrimkeEntityNotFoundRuntimeException;
import hr.java.restdatastock.model.entities.StavkaPrimkeEntity;
import hr.java.restdatastock.repositories.StavkaPrimkeRepository;
import hr.java.restdatastock.services.StavkaPrimkeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StavkaPrimkeServiceImpl implements StavkaPrimkeService {

    private final StavkaPrimkeRepository stavkaPrimkeRepository;
    @Override
    public List<StavkaPrimkeEntity> getAll() {
        return this.stavkaPrimkeRepository.findAll();
    }

    @Override
    public StavkaPrimkeEntity getOneById(Long id) {
        return stavkaPrimkeRepository.findById(id).orElseThrow(()-> new StavkaPrimkeEntityNotFoundRuntimeException(id));
    }

    @Override
    public StavkaPrimkeEntity createStavkaPrimke(final StavkaPrimkeEntity stavkaPrimke) {
        return this.stavkaPrimkeRepository.save(stavkaPrimke);
    }

    @Override
    public StavkaPrimkeEntity createStornoStavkePrimke(final StavkaPrimkeEntity stavkaPrimke, final Long id) {
        return this.stavkaPrimkeRepository.findById(id)
                .map(existingStavka -> {
                    existingStavka.setStavkaPrimkePrimka(stavkaPrimke.getStavkaPrimkePrimka());
                    existingStavka.setStavkaPrimkeRobe(stavkaPrimke.getStavkaPrimkeRobe());
                    existingStavka.setKolicina(stavkaPrimke.getKolicina());
                    existingStavka.setStorno(true);
                    existingStavka.setDatumStorno(LocalDate.now());
                    return this.stavkaPrimkeRepository.save(existingStavka);
                }).orElseThrow(()-> new StavkaPrimkeEntityExistsRuntimeException(stavkaPrimke));
    }

}
