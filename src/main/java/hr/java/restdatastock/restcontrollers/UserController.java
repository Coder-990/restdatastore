package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.model.dtos.UserDto;
import hr.java.restdatastock.model.entities.RoleEntity;
import hr.java.restdatastock.model.entities.UserEntity;
import hr.java.restdatastock.services.RoleService;
import hr.java.restdatastock.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    public static final String BASE_URL = "/users";
    private final ModelMapper modelMapper;
    private final UserService userService;



//    @PostConstruct
//    public void initRoleAndUser(){
//        this.userService.initRolesAndUsers();
//    }

    @PostMapping()
   public ResponseEntity<UserDto> registerNewUser(@RequestBody final UserDto userDto){
       UserEntity userEntity = this.userService.registerNewUser(convertToEntity(userDto));
       log.info("Role created successfully");
       return this.saveUserDtoResponseEntity(userEntity);
   }



    @GetMapping()
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }


//
//    @GetMapping("/{username}")
//    public ResponseEntity<UserEntity> findByUsername(@PathVariable String username) {
//        return ResponseEntity.ok().body(userService.getByUsername(username));
//    }
//
//    @PostMapping()
//    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
//        UserEntity userEntity = userService.saveUser(user);
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
//                .buildAndExpand(userEntity.getUsername()).toUriString());
//        return ResponseEntity.created(uri).build();
//    }
//
//
//    @PostMapping("/{username}/addRoleToUser")
//    public ResponseEntity<?> addRoleToUser(@PathVariable String username, @RequestBody RoleDto request) {
//        UserEntity userEntity = userService.addRoleToUser(username, request.getName());
//        return ResponseEntity.ok(userEntity);
//    }
//
//    @DeleteMapping("/{id}")
//    public HttpStatus delete(@PathVariable Long id) {
//        HttpStatus status = this.userService.deleteById(id);
//        log.info("User deleted successfully");
//        return status;
//    }

    private ResponseEntity<UserDto> getUserDtoResponseEntity(UserEntity user) {
        final ResponseEntity<UserDto> responseEntity;
        if (user != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(user));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return responseEntity;
    }

    private ResponseEntity<UserDto> saveUserDtoResponseEntity(final UserEntity user) {
        final ResponseEntity<UserDto> responseEntity;
        if (user != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(user));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }

    private UserEntity convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
    private UserDto convertToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

}

