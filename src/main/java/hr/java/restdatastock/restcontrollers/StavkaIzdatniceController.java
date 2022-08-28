package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.model.dtos.StavkaIzdatniceDto;
import hr.java.restdatastock.model.entities.StavkaIzdatniceEntity;
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
        List<StavkaIzdatniceEntity> itemShipments = this.stavkaIzdatniceService.getAll();
        log.info("Item shipments initialized successfully");
        return itemShipments;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StavkaIzdatniceDto> getById(@PathVariable final Long id) {
        StavkaIzdatniceEntity itemRecipient = this.stavkaIzdatniceService.getOneById(id);
        log.info("Item shipments fetched successfully by id");
        return this.getStavkaPrimkaDtoResponseEntity(itemRecipient);
    }

    @PostMapping()
    public ResponseEntity<StavkaIzdatniceDto> createStavkaIzdatnice(@RequestBody final StavkaIzdatniceDto stavkaIzdatniceDto) {
        final StavkaIzdatniceEntity itemRecipient = this.stavkaIzdatniceService.createStavkaIzdatnice(this.convertToEntity(stavkaIzdatniceDto));
        log.info("Item shipments created successfully");
        return this.getStavkaPrimkaDtoResponseEntity(itemRecipient);
    }

    @PutMapping()
    public ResponseEntity<StavkaIzdatniceDto> createStornoStavkeIzdatnice(@RequestBody final StavkaIzdatniceDto stavkaPrimkeDto) {
        final StavkaIzdatniceEntity cancelItemRecipient = this.stavkaIzdatniceService.createStornoStavkeIzdatnice(this.convertToEntity(stavkaPrimkeDto));
        log.info("Cancel item shipments created successfully");
        return this.getStavkaPrimkaDtoResponseEntity(cancelItemRecipient);
    }


    private ResponseEntity<StavkaIzdatniceDto> getStavkaPrimkaDtoResponseEntity(StavkaIzdatniceEntity stavkaIzdatnice) {
        final ResponseEntity<StavkaIzdatniceDto> responseEntity;
        if (stavkaIzdatnice != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(stavkaIzdatnice));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private StavkaIzdatniceDto convertToDto(StavkaIzdatniceEntity stavkaIzdatnice) {
        return modelMapper.map(stavkaIzdatnice, StavkaIzdatniceDto.class);
    }

    private StavkaIzdatniceEntity convertToEntity(StavkaIzdatniceDto stavkaIzdatniceDto) {
        return modelMapper.map(stavkaIzdatniceDto, StavkaIzdatniceEntity.class);
    }
}
