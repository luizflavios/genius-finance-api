package br.com.genius_finance.core.exception;

import br.com.genius_finance.model.dto.base.ErrorDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        var error = ErrorDTO.builder().error("Entity not found").detail(e.getMessage()).timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        var error = ErrorDTO.builder().error(e.getMessage()).detail(e.getLocalizedMessage()).build();
        return new ResponseEntity<>(error, CONFLICT);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorDTO> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        var error = ErrorDTO.builder().error(e.getMessage()).detail(e.getLocalizedMessage()).build();
        return new ResponseEntity<>(error, CONFLICT);
    }

}
