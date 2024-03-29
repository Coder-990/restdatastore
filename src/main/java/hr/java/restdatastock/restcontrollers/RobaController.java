package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.models.dtos.RobaDto;
import hr.java.restdatastock.models.entities.RobaEntity;
import hr.java.restdatastock.services.RobaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(RobaController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RobaController {

    public static final String BASE_URL = "/articles";

    private final ModelMapper modelMapper;

    private final RobaService robaService;

    @GetMapping()
    public List<RobaEntity> getAll() {
        final List<RobaEntity> articles = this.robaService.getAll();
        log.info("Articles initialized successfully");
        return articles;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RobaDto> getById(@PathVariable final Long id) {
        final RobaEntity article = this.robaService.getOneById(id);
        log.info("Article fetched successfully by id");
        return this.getRobaDtoResponseEntity(article);
    }

    @PostMapping()
    public ResponseEntity<RobaDto> create(@RequestBody final RobaDto robaDto) {
        final RobaEntity article = this.robaService.createArticle(this.convertToEntity(robaDto));
        log.info("Article created successfully");
        return this.saveRobaDtoResponseEntity(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RobaDto> update(@RequestBody final RobaDto robaDto, @PathVariable final Long id) {
        final RobaEntity article = this.robaService.updateExistingArticle(this.convertToEntity(robaDto), id);
        log.info("Article updated successfully");
        return this.saveRobaDtoResponseEntity(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RobaDto> delete(@PathVariable final Long id) {
        final HttpStatus status = this.robaService.deleteById(id);
        log.info("Article deleted successfully");
        return ResponseEntity.status(status).build();
    }

    private ResponseEntity<RobaDto> getRobaDtoResponseEntity(final RobaEntity roba) {
        final ResponseEntity<RobaDto> responseEntity;
        if (roba != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(roba));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private ResponseEntity<RobaDto> saveRobaDtoResponseEntity(final RobaEntity robaEntity) {
        final ResponseEntity<RobaDto> responseEntity;
        if (robaEntity != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(robaEntity));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }

    private RobaDto convertToDto(final RobaEntity robaEntity) {
        return modelMapper.map(robaEntity, RobaDto.class);
    }

    private RobaEntity convertToEntity(final RobaDto robaDto) {
        return modelMapper.map(robaDto, RobaEntity.class);
    }
}
