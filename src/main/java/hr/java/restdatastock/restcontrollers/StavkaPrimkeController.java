package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.model.dtos.StavkaPrimkeDto;
import hr.java.restdatastock.model.entities.StavkaPrimkeEntity;
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

    public static final String BASE_URL = "/itemRecipients";
    private final ModelMapper modelMapper;
    private final StavkaPrimkeService stavkaPrimkeService;

    @GetMapping()
    public List<StavkaPrimkeEntity> getAll() {
        List<StavkaPrimkeEntity> itemRecipients = this.stavkaPrimkeService.getAll();
        log.info("Item recipients initialized successfully");
        return itemRecipients;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StavkaPrimkeDto> getById(@PathVariable final Long id) {
        StavkaPrimkeEntity itemRecipient = this.stavkaPrimkeService.getOneById(id);
        log.info("Item recipient fetched successfully by id");
        return this.getStavkaPrimkaDtoResponseEntity(itemRecipient);
    }

    @PostMapping()
    public ResponseEntity<StavkaPrimkeDto> create(@RequestBody final StavkaPrimkeDto primkaDto) {
        final StavkaPrimkeEntity itemRecipient = this.stavkaPrimkeService.createStavkaPrimke(this.convertToEntity(primkaDto));
        log.info("Item recipient created successfully");
        return this.getStavkaPrimkaDtoResponseEntity(itemRecipient);
    }

    @PutMapping()
    public ResponseEntity<StavkaPrimkeDto> createStornoStavkePrimke(@RequestBody final StavkaPrimkeDto stavkaPrimkeDto) {
        final StavkaPrimkeEntity cancelItemRecipient = this.stavkaPrimkeService.createStornoStavkePrimke(this.convertToEntity(stavkaPrimkeDto));
        log.info("Cancel item recipient created successfully");
        return this.getStavkaPrimkaDtoResponseEntity(cancelItemRecipient);
    }


    private ResponseEntity<StavkaPrimkeDto> getStavkaPrimkaDtoResponseEntity(StavkaPrimkeEntity stavkaPrimke) {
        final ResponseEntity<StavkaPrimkeDto> responseEntity;
        if (stavkaPrimke != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(stavkaPrimke));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private StavkaPrimkeDto convertToDto(StavkaPrimkeEntity stavkaPrimke) {
        return modelMapper.map(stavkaPrimke, StavkaPrimkeDto.class);
    }

    private StavkaPrimkeEntity convertToEntity(StavkaPrimkeDto stavkaPrimkeDto) {
        return modelMapper.map(stavkaPrimkeDto, StavkaPrimkeEntity.class);
    }
}
