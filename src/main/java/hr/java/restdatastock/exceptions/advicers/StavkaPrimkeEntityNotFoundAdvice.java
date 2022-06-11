package hr.java.restdatastock.exceptions.advicers;

import hr.java.restdatastock.exceptions.StavkaPrimkeEntityNotFoundRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StavkaPrimkeEntityNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(StavkaPrimkeEntityNotFoundRuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String stavkaPrimkeNotFoundHandler(StavkaPrimkeEntityNotFoundRuntimeException ex) {
        return ex.getMessage();
    }
}
