package org.sam.prueba.common.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = {"org.sam.prueba.superheroes.controllers"})
@ResponseBody
public class ExceptionHelper {

    private final static Logger logger = Logger.getLogger("org.sam.prueba.common.exceptions.ExceptionHelper");

    @ExceptionHandler(value = {AuthenticationException .class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage handleUnauthorizedException(AuthenticationException  ex) {
        logger.log(Level.SEVERE, "Unauthorized Exception: {0}", ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(value = {InvalidInputException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidInputException(InvalidInputException ex) {
        logger.log(Level.SEVERE, "Invalid Input Exception: {0}", ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED)
    public ErrorMessage handleBusinessException(BusinessException ex) {
        logger.log(Level.SEVERE, "Business Exception: {0}", ex.getMessage());
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(value = {RuntimeException.class, Exception.class, Throwable.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Throwable ex) {
        logger.log(Level.SEVERE, "Exception: {0}", ex.getMessage());
        return new ErrorMessage("Ups algo no fue bien");
    }

}
