package hr.java.restdatastock.services;

import hr.java.restdatastock.model.entities.JwtRequestEntity;
import hr.java.restdatastock.model.entities.JwtResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface JwtService {
    JwtResponseEntity createJwtToken(JwtRequestEntity jwtRequestEntity);

//    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
