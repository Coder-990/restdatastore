package hr.java.restdatastock.exceptions.advicers;
import hr.java.restdatastock.exceptions.StavkaIzdatniceEntityNotFoundRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StavkaIzdatniceEntityNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(StavkaIzdatniceEntityNotFoundRuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String stavkaIzdatniceNotFoundHandler(StavkaIzdatniceEntityNotFoundRuntimeException ex) {
        return ex.getMessage();
    }
}
