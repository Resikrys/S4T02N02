package cat.itacademy.s04.t02.n02.exceptions;

import cat.itacademy.s04.t02.n02.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(NotFoundException ex, WebRequest req) {
        var err = new ErrorDTO(ex.getMessage(), req.getDescription(false).replace("uri=", ""), OffsetDateTime.now(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DuplicateFruitException.class)
    public ResponseEntity<ErrorDTO> handleDuplicate(DuplicateFruitException ex, WebRequest req) {
        var err = new ErrorDTO(ex.getMessage(), req.getDescription(false).replace("uri=", ""), OffsetDateTime.now(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAll(Exception ex, WebRequest req) {
        var err = new ErrorDTO("Internal server error", req.getDescription(false).replace("uri=", ""), OffsetDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
