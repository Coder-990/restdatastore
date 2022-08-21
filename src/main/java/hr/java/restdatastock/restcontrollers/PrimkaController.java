package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.model.dtos.PrimkaDto;
import hr.java.restdatastock.model.entities.PrimkaEntity;
import hr.java.restdatastock.services.PrimkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(PrimkaController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PrimkaController {

    public static final String BASE_URL = "/receipts";
    private final ModelMapper modelMapper;
    private final PrimkaService primkaService;

    @GetMapping()
    public List<PrimkaEntity> getAll() {
        List<PrimkaEntity> recipients = this.primkaService.getAll();
        log.info("Recipients initialized successfully");
        return recipients;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrimkaDto> getById(@PathVariable final Long id) {
        PrimkaEntity recipient = this.primkaService.getOneById(id);
        log.info("Recipient fetched successfully by id");
        return this.getPrimkaDtoResponseEntity(recipient);
    }

    @PostMapping()
    public ResponseEntity<PrimkaDto> create(@RequestBody final PrimkaDto primkaDto) {
        final PrimkaEntity recipient = this.primkaService.createPrimka(this.convertToEntity(primkaDto));
        log.info("Recipient created successfully");
        return this.getPrimkaDtoResponseEntity(recipient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PrimkaDto> delete(@PathVariable Long id) {
        HttpStatus status = this.primkaService.deleteById(id);
        log.info("Recipient deleted successfully");
        return ResponseEntity.status(status).build();
    }

    private ResponseEntity<PrimkaDto> getPrimkaDtoResponseEntity(PrimkaEntity primka) {
        final ResponseEntity<PrimkaDto> responseEntity;
        if (primka != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(primka));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private PrimkaDto convertToDto(PrimkaEntity primkaEntity) {
        return modelMapper.map(primkaEntity, PrimkaDto.class);
    }

    private PrimkaEntity convertToEntity(PrimkaDto primkaDto) {
        return modelMapper.map(primkaDto, PrimkaEntity.class);
    }
}
