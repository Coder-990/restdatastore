package hr.java.restdatastock.exceptions.advicers;

import hr.java.restdatastock.exceptions.FirmeEntityExistsRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FirmeExistsAdvice {

    @ResponseBody
    @ExceptionHandler(FirmeEntityExistsRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String firmaExistsHandler(FirmeEntityExistsRuntimeException ex) {
        return ex.getMessage();
    }
}
