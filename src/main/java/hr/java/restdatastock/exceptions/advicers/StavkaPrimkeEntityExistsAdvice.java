package hr.java.restdatastock.exceptions.advicers;

import hr.java.restdatastock.exceptions.StavkaPrimkeEntityExistsRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StavkaPrimkeEntityExistsAdvice {

    @ResponseBody
    @ExceptionHandler(StavkaPrimkeEntityExistsRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String stavkaPrimkeExistsHandler(StavkaPrimkeEntityExistsRuntimeException ex) {
        return ex.getMessage();
    }
}
