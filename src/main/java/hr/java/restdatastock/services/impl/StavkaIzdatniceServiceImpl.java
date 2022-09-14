package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.exceptions.StavkaIzdatniceEntityExistsRuntimeException;
import hr.java.restdatastock.exceptions.StavkaIzdatniceEntityNotFoundRuntimeException;
import hr.java.restdatastock.models.entities.StavkaIzdatniceEntity;
import hr.java.restdatastock.repositories.StavkaIzdatniceRepository;
import hr.java.restdatastock.services.StavkaIzdatniceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StavkaIzdatniceServiceImpl implements StavkaIzdatniceService {

    private final StavkaIzdatniceRepository stavkaIzdatniceRepository;
    @Override
    public List<StavkaIzdatniceEntity> getAll() {
        return this.stavkaIzdatniceRepository.findAll();
    }

    @Override
    public StavkaIzdatniceEntity getOneById(final Long id) {
        return this.stavkaIzdatniceRepository.findById(id).orElseThrow(()-> new StavkaIzdatniceEntityNotFoundRuntimeException(id));
    }

    @Override
    public StavkaIzdatniceEntity createStavkaIzdatnice(final StavkaIzdatniceEntity izdatnica) {
        return this.stavkaIzdatniceRepository.save(izdatnica);
    }

    @Override
    public StavkaIzdatniceEntity createStornoStavkeIzdatnice(final StavkaIzdatniceEntity stavkaIzdatnice,
                                                             final Long id) {
        return stavkaIzdatniceRepository.findById(id)
                .map(existingStavka -> {
                    existingStavka.setStavkaIzdatniceIzdatnica(stavkaIzdatnice.getStavkaIzdatniceIzdatnica());
                    existingStavka.setStavkaIzdatniceRobe(stavkaIzdatnice.getStavkaIzdatniceRobe());
                    existingStavka.setKolicina(stavkaIzdatnice.getKolicina());
                    existingStavka.setStorno(true);
                    existingStavka.setDatumStorno(LocalDate.now());
                    return this.stavkaIzdatniceRepository.save(existingStavka);
                }).orElseThrow(()-> new StavkaIzdatniceEntityExistsRuntimeException(stavkaIzdatnice));
    }
}
