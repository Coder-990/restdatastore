package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.models.dtos.StavkaIzdatniceDto;
import hr.java.restdatastock.models.entities.StavkaIzdatniceEntity;
import hr.java.restdatastock.services.StavkaIzdatniceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(StavkaIzdatniceController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StavkaIzdatniceController {

    public static final String BASE_URL = "/itemShipments";
    private final ModelMapper modelMapper;
    private final StavkaIzdatniceService stavkaIzdatniceService;

    @GetMapping()
    public List<StavkaIzdatniceEntity> getAll() {
        final List<StavkaIzdatniceEntity> itemShipments = this.stavkaIzdatniceService.getAll();
        log.info("Item shipments initialized successfully");
        return itemShipments;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StavkaIzdatniceDto> getById(@PathVariable final Long id) {
        final StavkaIzdatniceEntity itemShipment = this.stavkaIzdatniceService.getOneById(id);
        log.info("Item shipments fetched successfully by id");
        return this.getStavkaPrimkaDtoResponseEntity(itemShipment);
    }

    @PostMapping()
    public ResponseEntity<StavkaIzdatniceDto> createStavkaIzdatnice(
            @RequestBody final StavkaIzdatniceDto stavkaIzdatniceDto) {
        final StavkaIzdatniceEntity itemShipment = this.stavkaIzdatniceService.createStavkaIzdatnice(
                this.convertToEntity(stavkaIzdatniceDto));
        log.info("Item shipments created successfully");
        return this.saveStavkaIzdatniceDtoResponseEntity(itemShipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StavkaIzdatniceDto> createStornoStavkeIzdatnice(
            @RequestBody final StavkaIzdatniceDto stavkaIzdatniceDto, @PathVariable final Long id) {
        final StavkaIzdatniceEntity cancelItemShipment = this.stavkaIzdatniceService.createStornoStavkeIzdatnice(
                this.convertToEntity(stavkaIzdatniceDto), id);
        log.info("Cancel item shipments created successfully");
        return this.saveStavkaIzdatniceDtoResponseEntity(cancelItemShipment);
    }


    private ResponseEntity<StavkaIzdatniceDto> getStavkaPrimkaDtoResponseEntity(
            final StavkaIzdatniceEntity stavkaIzdatnice) {
        final ResponseEntity<StavkaIzdatniceDto> responseEntity;
        if (stavkaIzdatnice != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(stavkaIzdatnice));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private ResponseEntity<StavkaIzdatniceDto> saveStavkaIzdatniceDtoResponseEntity(
            final StavkaIzdatniceEntity stavkaIzdatniceEntity) {
        final ResponseEntity<StavkaIzdatniceDto> responseEntity;
        if (stavkaIzdatniceEntity != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(stavkaIzdatniceEntity));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }

    private StavkaIzdatniceDto convertToDto(final StavkaIzdatniceEntity stavkaIzdatnice) {
        return modelMapper.map(stavkaIzdatnice, StavkaIzdatniceDto.class);
    }

    private StavkaIzdatniceEntity convertToEntity(final StavkaIzdatniceDto stavkaIzdatniceDto) {
        return modelMapper.map(stavkaIzdatniceDto, StavkaIzdatniceEntity.class);
    }
}
