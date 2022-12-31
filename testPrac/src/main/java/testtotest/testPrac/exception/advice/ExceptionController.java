package testtotest.testPrac.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import testtotest.testPrac.exception.*;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OverApplyException.class)
    public ErrorResult handlerOverApplyException(OverApplyException e) {
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OverDateException.class)
    public ErrorResult handlerOverDateException(OverDateException e) {
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DoneException.class)
    public ErrorResult handlerDoneException(DoneException e) {
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadySubmitException.class)
    public ErrorResult handlerAlreaySubmitException(AlreadySubmitException e){
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BeforeSubmitException.class)
    public ErrorResult handlerBeforeSubmitException(BeforeSubmitException e){
        return new ErrorResult(e.getMessage());
    }
}
