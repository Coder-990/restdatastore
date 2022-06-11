package hr.java.restdatastock.exceptions.advicers;

import hr.java.restdatastock.exceptions.RobaEntityExistsRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RobaEntityExistsAdvice {

    @ResponseBody
    @ExceptionHandler(RobaEntityExistsRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String robaExistsHandler(RobaEntityExistsRuntimeException ex) {
        return ex.getMessage();
    }
}
