package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.models.dtos.IzdatnicaDto;
import hr.java.restdatastock.models.entities.IzdatnicaEntity;
import hr.java.restdatastock.services.IzdatnicaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(IzdatnicaController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class IzdatnicaController {

    public static final String BASE_URL = "/shipments";
    private final ModelMapper modelMapper;
    private final IzdatnicaService izdatnicaService;

    @GetMapping()
    public List<IzdatnicaEntity> getAll() {
        final List<IzdatnicaEntity> shipments = this.izdatnicaService.getAll();
        log.info("Shipments initialized successfully");
        return shipments;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IzdatnicaDto> getById(@PathVariable final Long id) {
        final IzdatnicaEntity shipment = this.izdatnicaService.getOneById(id);
        log.info("Shipment fetched successfully by id");
        return this.getIzdatnicaDtoResponseEntity(shipment);
    }

    @PostMapping()
    public ResponseEntity<IzdatnicaDto> create(@RequestBody final IzdatnicaDto izdatnicaDto) {
        final IzdatnicaEntity shipment = this.izdatnicaService.createIzdatnica(this.convertToEntity(izdatnicaDto));
        log.info("Shipment created successfully");
        return this.saveIzdatnicaDtoResponseEntity(shipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IzdatnicaDto> delete(@PathVariable Long id) {
        final HttpStatus status = this.izdatnicaService.deleteById(id);
        log.info("Shipment deleted successfully");
        return ResponseEntity.status(status).build();
    }

    private ResponseEntity<IzdatnicaDto> getIzdatnicaDtoResponseEntity(final IzdatnicaEntity izdatnica) {
        final ResponseEntity<IzdatnicaDto> responseEntity;
        if (izdatnica != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(izdatnica));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private ResponseEntity<IzdatnicaDto> saveIzdatnicaDtoResponseEntity(final IzdatnicaEntity izdatnica) {
        final ResponseEntity<IzdatnicaDto> responseEntity;
        if (izdatnica != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(izdatnica));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }

    private IzdatnicaDto convertToDto(final IzdatnicaEntity izdatnicaEntity) {
        return modelMapper.map(izdatnicaEntity, IzdatnicaDto.class);
    }

    private IzdatnicaEntity convertToEntity(final IzdatnicaDto izdatnicaDto) {
        return modelMapper.map(izdatnicaDto, IzdatnicaEntity.class);
    }
}
