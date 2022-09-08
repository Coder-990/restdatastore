package hr.java.restdatastock.services;

//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.proc.BadJOSEException;
import hr.java.restdatastock.model.entities.UserEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {


    List<UserEntity> getAll();

    UserEntity getByUsername(String username);

    UserEntity registerNewUser(UserEntity user);

    UserEntity addRoleToUser(String username, String roleName);
//    Map<String,String> refreshToken(String authorizationHeader, String issuer) throws BadJOSEException, ParseException, JOSEException, java.text.ParseException;

    HttpStatus deleteById(Long id);
}
