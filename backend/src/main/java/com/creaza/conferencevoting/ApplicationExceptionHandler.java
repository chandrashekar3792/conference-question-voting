package com.creaza.conferencevoting;

import com.creaza.conferencevoting.exception.IncorrectChoiceException;
import com.creaza.conferencevoting.exception.ResourceNotFoundException;
import com.creaza.conferencevoting.model.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleGenericError(Exception ex, WebRequest request) {
        log.warn("Handling GenericException: request - {}, error - {}", request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundError(Exception ex, WebRequest request) {
        log.warn("Handling ResourceNotFoundException: request - {}, error - {}", request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectChoiceException.class)
    public final ResponseEntity<Object> handleIncorrectChoiceError(Exception ex, WebRequest request) {
        log.warn("Handling IncorrectChoiceException: request - {}, error - {}", request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public final ResponseEntity<Object> handleOptimisticLockFailureError(OptimisticLockingFailureException ex, WebRequest request) {
        log.warn("Handling OptimisticLockingFailureException: request - {}, error - {}", request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.CONFLICT);
    }
}
