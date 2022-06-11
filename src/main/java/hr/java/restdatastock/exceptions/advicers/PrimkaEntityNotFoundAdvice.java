package hr.java.restdatastock.exceptions.advicers;

import hr.java.restdatastock.exceptions.PrimkaEntityNotFoundRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PrimkaEntityNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PrimkaEntityNotFoundRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String primkaNotFoundHandler(PrimkaEntityNotFoundRuntimeException ex) {
        return ex.getMessage();
    }
}
