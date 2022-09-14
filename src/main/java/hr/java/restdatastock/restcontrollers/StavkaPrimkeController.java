package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.models.dtos.StavkaIzdatniceDto;
import hr.java.restdatastock.models.dtos.StavkaPrimkeDto;
import hr.java.restdatastock.models.entities.StavkaIzdatniceEntity;
import hr.java.restdatastock.models.entities.StavkaPrimkeEntity;
import hr.java.restdatastock.services.StavkaPrimkeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(StavkaPrimkeController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StavkaPrimkeController {

    public static final String BASE_URL = "/itemReceipts";
    private final ModelMapper modelMapper;
    private final StavkaPrimkeService stavkaPrimkeService;

    @GetMapping()
    public List<StavkaPrimkeEntity> getAll() {
        final List<StavkaPrimkeEntity> itemReceipts = this.stavkaPrimkeService.getAll();
        log.info("Item recipients initialized successfully");
        return itemReceipts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StavkaPrimkeDto> getById(@PathVariable final Long id) {
        final StavkaPrimkeEntity itemReceipt = this.stavkaPrimkeService.getOneById(id);
        log.info("Item recipient fetched successfully by id");
        return this.getStavkaPrimkaDtoResponseEntity(itemReceipt);
    }

    @PostMapping()
    public ResponseEntity<StavkaPrimkeDto> create(@RequestBody final StavkaPrimkeDto primkaDto) {
        final StavkaPrimkeEntity itemReceipt = this.stavkaPrimkeService.createStavkaPrimke(
                this.convertToEntity(primkaDto));
        log.info("Item recipient created successfully");
        return this.saveStavkaPrimkeDtoResponseEntity(itemReceipt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StavkaPrimkeDto> createStornoStavkePrimke(
            @RequestBody final StavkaPrimkeDto stavkaPrimkeDto, @PathVariable final Long id) {
        final StavkaPrimkeEntity cancelItemReceipt = this.stavkaPrimkeService.createStornoStavkePrimke(
                this.convertToEntity(stavkaPrimkeDto), id);
        log.info("Cancel item recipient created successfully");
        return this.saveStavkaPrimkeDtoResponseEntity(cancelItemReceipt);
    }


    private ResponseEntity<StavkaPrimkeDto> getStavkaPrimkaDtoResponseEntity(
            final StavkaPrimkeEntity stavkaPrimke) {
        final ResponseEntity<StavkaPrimkeDto> responseEntity;
        if (stavkaPrimke != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(stavkaPrimke));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private ResponseEntity<StavkaPrimkeDto> saveStavkaPrimkeDtoResponseEntity(
            final StavkaPrimkeEntity stavkaPrimkeEntity) {
        final ResponseEntity<StavkaPrimkeDto> responseEntity;
        if (stavkaPrimkeEntity != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(stavkaPrimkeEntity));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }

    private StavkaPrimkeDto convertToDto(final StavkaPrimkeEntity stavkaPrimke) {
        return modelMapper.map(stavkaPrimke, StavkaPrimkeDto.class);
    }

    private StavkaPrimkeEntity convertToEntity(final StavkaPrimkeDto stavkaPrimkeDto) {
        return modelMapper.map(stavkaPrimkeDto, StavkaPrimkeEntity.class);
    }
}
