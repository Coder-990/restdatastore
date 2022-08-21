package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.model.dtos.FirmeDto;
import hr.java.restdatastock.model.entities.FirmeEntity;
import hr.java.restdatastock.services.FirmeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(FirmeController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FirmeController extends ResponseEntityExceptionHandler {

    public static final String BASE_URL = "/companies";

    private final FirmeService firmeService;

    private final ModelMapper modelMapper;

    @GetMapping()
    public List<FirmeDto> getAll() {
        List<FirmeDto> companiesDto = this.firmeService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
        log.info("Companies initialized successfully");
        return companiesDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FirmeDto> getById(@PathVariable final Long id) {
        FirmeEntity firma = this.firmeService.getOneById(id);
        log.info("Company fetched successfully by id");
        return this.getFirmeDtoResponseEntity(firma);
    }

    @PostMapping()
    public ResponseEntity<FirmeDto> create(@RequestBody final FirmeDto firmeDto) {
        final FirmeEntity firma = this.firmeService.createFirma(convertToEntity(firmeDto));
        log.info("Company created successfully");
        return this.saveFirmeDtoResponseEntity(firma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FirmeDto> update(@RequestBody final FirmeDto firmeDto, @PathVariable final Long id) {
        final FirmeEntity firma = this.firmeService.updateExistingFirma(convertToEntity(firmeDto), id);
        log.info("Company updated successfully");
        return this.saveFirmeDtoResponseEntity(firma);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        HttpStatus status = this.firmeService.deleteById(id);
        log.info("Company deleted successfully");
        return status;
    }


    private ResponseEntity<FirmeDto> getFirmeDtoResponseEntity(final FirmeEntity firma) {
        final ResponseEntity<FirmeDto> responseEntity;
        if (firma != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(firma));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return responseEntity;
    }

    private ResponseEntity<FirmeDto> saveFirmeDtoResponseEntity(final FirmeEntity firma) {
        final ResponseEntity<FirmeDto> responseEntity;
        if (firma != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(firma));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }


    private FirmeEntity convertToEntity(final FirmeDto firmeDto) {
        return modelMapper.map(firmeDto, FirmeEntity.class);
    }

    private FirmeDto convertToDto(final FirmeEntity firmeEntity) {
        return modelMapper.map(firmeEntity, FirmeDto.class);
    }
}
