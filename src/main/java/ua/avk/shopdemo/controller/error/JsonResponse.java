package ua.avk.shopdemo.controller.error;

import lombok.*;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class JsonResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private List<ValidationError> errors;

    public JsonResponse() {
        timestamp = LocalDateTime.now();
    }

    public JsonResponse(String message) {
        this();
        this.message = message;
    }

    public JsonResponse(int status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public JsonResponse(int status, String message, List<ValidationError> errors) {
        this();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public static JsonResponse success(String message) {
        return new JsonResponse(message);
    }

    public static JsonResponse failure(int status, String message) {
        return new JsonResponse(status, message);
    }

    public static JsonResponse failure(int status, String message, List<ValidationError> errors) {
        return new JsonResponse(status, message, errors);
    }

    public static JsonResponse failure(int status, Throwable exception) {
        JsonResponse failResponse = new JsonResponse();
        failResponse.setStatus(status);
        failResponse.setMessage(ExceptionUtils.getRootCauseMessage(exception));
        return failResponse;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class ValidationError {
        private String field;
        @NonNull
        private String message;
    }
}
