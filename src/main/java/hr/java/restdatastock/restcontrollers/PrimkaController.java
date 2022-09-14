package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.models.dtos.IzdatnicaDto;
import hr.java.restdatastock.models.dtos.PrimkaDto;
import hr.java.restdatastock.models.entities.IzdatnicaEntity;
import hr.java.restdatastock.models.entities.PrimkaEntity;
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
        final List<PrimkaEntity> receipts = this.primkaService.getAll();
        log.info("Recipients initialized successfully");
        return receipts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrimkaDto> getById(@PathVariable final Long id) {
        final PrimkaEntity reeceipt = this.primkaService.getOneById(id);
        log.info("Recipient fetched successfully by id");
        return this.getPrimkaDtoResponseEntity(reeceipt);
    }

    @PostMapping()
    public ResponseEntity<PrimkaDto> create(@RequestBody final PrimkaDto primkaDto) {
        final PrimkaEntity receipt = this.primkaService.createPrimka(this.convertToEntity(primkaDto));
        log.info("Recipient created successfully");
        return this.savePrimkaDtoResponseEntity(receipt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PrimkaDto> delete(@PathVariable final Long id) {
        HttpStatus status = this.primkaService.deleteById(id);
        log.info("Recipient deleted successfully");
        return ResponseEntity.status(status).build();
    }

    private ResponseEntity<PrimkaDto> getPrimkaDtoResponseEntity(final PrimkaEntity primka) {
        final ResponseEntity<PrimkaDto> responseEntity;
        if (primka != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(primka));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private ResponseEntity<PrimkaDto> savePrimkaDtoResponseEntity(final PrimkaEntity primka) {
        final ResponseEntity<PrimkaDto> responseEntity;
        if (primka != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(primka));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }

    private PrimkaDto convertToDto(final PrimkaEntity primkaEntity) {
        return modelMapper.map(primkaEntity, PrimkaDto.class);
    }

    private PrimkaEntity convertToEntity(final PrimkaDto primkaDto) {
        return modelMapper.map(primkaDto, PrimkaEntity.class);
    }
}
