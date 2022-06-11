package hr.java.restdatastock.exceptions.advicers;

import hr.java.restdatastock.exceptions.StavkaIzdatniceEntityExistsRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StavkaIzdatniceEntityExistsAdvice {

    @ResponseBody
    @ExceptionHandler(StavkaIzdatniceEntityExistsRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String stavkaIzdatniceExistsHandler(StavkaIzdatniceEntityExistsRuntimeException ex) {
        return ex.getMessage();
    }
}
