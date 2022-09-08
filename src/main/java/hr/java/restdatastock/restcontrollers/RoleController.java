package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.model.dtos.RoleDto;
import hr.java.restdatastock.model.entities.RoleEntity;
import hr.java.restdatastock.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(RoleController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController extends ResponseEntityExceptionHandler {

    protected static final String BASE_URL = "/roles";
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<RoleDto> createRole(@RequestBody final RoleDto roleDto) {
        RoleEntity saveRole = this.roleService.saveRole(this.convertToEntity(roleDto));
        log.info("Role created successfully");
        return this.saveRoleDtoResponseEntity(saveRole);
    }


    private ResponseEntity<RoleDto> saveRoleDtoResponseEntity(final RoleEntity role) {
        final ResponseEntity<RoleDto> responseEntity;
        if (role != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(role));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }


    private RoleEntity convertToEntity(final RoleDto roleDto) {
        return modelMapper.map(roleDto, RoleEntity.class);
    }

    private RoleDto convertToDto(final RoleEntity roleEntity) {
        return modelMapper.map(roleEntity, RoleDto.class);
    }
}
