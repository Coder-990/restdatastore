package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.model.entities.JwtRequestEntity;
import hr.java.restdatastock.model.entities.JwtResponseEntity;
import hr.java.restdatastock.services.impl.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class JwtController {

    private final JwtServiceImpl jwtService;

    @PostMapping("{/authenticate}")
    public JwtResponseEntity createJwtToken(@RequestBody final JwtRequestEntity jwtRequestEntity){
        return this.jwtService.createJwtToken(jwtRequestEntity);

    }
}
