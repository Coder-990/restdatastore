package hr.java.restdatastock.exceptions.advicers;

import hr.java.restdatastock.exceptions.FirmeEntityNotFoundRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FirmeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(FirmeEntityNotFoundRuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String firmaNotFoundHandler(FirmeEntityNotFoundRuntimeException ex) {
        return ex.getMessage();
    }
}
