package ua.avk.shopdemo.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ua.avk.shopdemo.controller.error.JsonResponse.ValidationError;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Handle exception in API
 * <br>
 * ConstraintViolationException.class,
 * DataIntegrityViolationException.class,
 * MethodArgumentNotValidException.class,
 * ConversionFailedException.class,
 * EntityNotFoundException.class,
 * ObjectNotFoundException.class,
 * InvalidDataAccessApiUsageException.class,
 * DataAccessException.class,
 * NullPointerException.class,
 * IllegalArgumentException.class,
 * IllegalStateException.class
 */
@Slf4j
@ControllerAdvice
public class ApiException {

    /**
     * Handle exception
     *
     * @param ex      RuntimeException obj
     * @param request WebRequest obj
     * @return ResponseEntity obj with status BAD_REQUEST
     * @see org.hibernate.exception.ConstraintViolationException
     * @see org.springframework.dao.DataIntegrityViolationException
     */
    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, null, null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle exception
     *
     * @param ex      MethodArgumentNotValidException obj
     * @param request WebRequest obj
     * @return ResponseEntity obj with status BAD_REQUEST
     * @see org.springframework.web.bind.MethodArgumentNotValidException
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<JsonResponse> invalidInput(MethodArgumentNotValidException ex, final WebRequest request) {
        BindingResult result = ex.getBindingResult();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "Validation Error", fromBindingErrors(result)));
    }

    /**
     * Handle exception
     *
     * @param ex      ConversionFailedException obj
     * @param request WebRequest obj
     * @return ResponseEntity obj with status BAD_REQUEST
     * @see org.springframework.core.convert.ConversionFailedException
     */
    @ExceptionHandler({ConversionFailedException.class})
    public ResponseEntity<Object> invalidInput(ConversionFailedException ex, final WebRequest request) {
        return handleExceptionInternal(ex, null, null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle exception
     *
     * @param ex      RuntimeException obj
     * @param request WebRequest obj
     * @return ResponseEntity obj with status NOT_FOUND
     * @see javax.persistence.EntityNotFoundException
     * @see org.hibernate.ObjectNotFoundException
     */
    @ExceptionHandler(value = {EntityNotFoundException.class, ObjectNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND, request);
    }

    /**
     * Handle exception
     *
     * @param ex      RuntimeException obj
     * @param request WebRequest obj
     * @return ResponseEntity obj with status CONFLICT
     * @see org.springframework.dao.InvalidDataAccessApiUsageException
     * @see org.springframework.dao.DataAccessException
     */
    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, null, null, HttpStatus.CONFLICT, request);
    }

    /**
     * Handle exception
     *
     * @param ex      RuntimeException obj
     * @param request WebRequest obj
     * @return ResponseEntity obj with status INTERNAL_SERVER_ERROR
     * @see java.lang.NullPointerException
     * @see java.lang.IllegalArgumentException
     * @see java.lang.IllegalStateException
     */
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, null, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * Handle exception
     *
     * @param ex      Exception obj
     * @param body    Object
     * @param headers {@link org.springframework.http#HttpHeaders HttpHeaders}
     * @param status  {@link org.springframework.http#HttpStatus status}
     * @param request {@link org.springframework.web.context.request#WebRequest request}
     * @return ResponseEntity obj with exception in the body
     */
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("===Error:", ex);
        return ResponseEntity.status(status).body(JsonResponse.failure(status.value(), ex));
    }

    /**
     * Handle validation
     *
     * @param errors {@link org.springframework.validation#Errors errors} obj of the validation
     * @return list error of the validation
     */
    private List<ValidationError> fromBindingErrors(Errors errors) {
        List<ValidationError> globalErrors = errors.getGlobalErrors().stream().map(error -> new ValidationError(error.getDefaultMessage())).collect(Collectors.toList());
        List<ValidationError> fieldErrors = errors.getFieldErrors().stream().map(error -> new ValidationError(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());

        globalErrors.addAll(fieldErrors);
        return globalErrors;
    }
}
