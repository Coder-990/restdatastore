package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.exceptions.PrimkaEntityNotFoundRuntimeException;
import hr.java.restdatastock.models.entities.PrimkaEntity;
import hr.java.restdatastock.repositories.PrimkaRepository;
import hr.java.restdatastock.services.PrimkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrimkaServiceImpl implements PrimkaService {

    private final PrimkaRepository primkaRepository;

    @Override
    public List<PrimkaEntity> getAll(){
        return  this.primkaRepository.findAll();
    }

    @Override
    public PrimkaEntity getOneById(final Long id) {
        return primkaRepository.findById(id).orElseThrow(() -> new PrimkaEntityNotFoundRuntimeException(id));
    }

    @Override
    public PrimkaEntity createPrimka(final PrimkaEntity primka){
        return this.primkaRepository.save(primka);
    }

    @Override
    public HttpStatus deleteById(Long id) {
        final HttpStatus httpStatus;
        if (this.primkaRepository.deletePrimkaEntityById(id) > 0){
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return httpStatus;
    }

//    @Override
//    public void deletePrimka(final Long id){
//        this.primkaRepository.deleteById(id);
//    }
}
