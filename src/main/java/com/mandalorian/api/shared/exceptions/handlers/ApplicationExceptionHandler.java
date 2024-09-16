package com.mandalorian.api.shared.exceptions.handlers;

import com.mandalorian.api.shared.enums.ErrorCode;
import com.mandalorian.api.shared.exceptions.ApplicationException;
import com.mandalorian.api.shared.responses.http.ApiErrorResponse;
import com.mandalorian.api.shared.responses.http.KOResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<KOResponse> handleApplicationException(
        final ApplicationException exception, final HttpServletRequest request
    ) {
        var guid = UUID.randomUUID().toString();
        log.error(
            String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()),
            exception
        );

        final KOResponse response = KOResponse.builder()
            .message(exception.getErrorCode())
            .errors(List.of(exception.getMessage()))
            .build();

        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<KOResponse> handleApplicationException(
        final HttpClientErrorException exception, final HttpServletRequest request
    ) {
        var guid = UUID.randomUUID().toString();
        log.error(
            String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()),
            exception
        );

        final KOResponse response = KOResponse.builder()
            .message(exception.getStatusText())
            .errors(List.of(exception.getMessage()))
            .build();

        return new ResponseEntity<>(response, exception.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnknownException(
        final Exception exception, final HttpServletRequest request
    ) {
        var guid = UUID.randomUUID().toString();
        log.error(
            String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()),
            exception
        );

        final ApiErrorResponse response = ApiErrorResponse.builder()
            .guid(guid)
            .errorCode(ErrorCode.INTERNAL_SERVER_ERROR.value())
            .message("Internal Server Error")
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .statusName(HttpStatus.INTERNAL_SERVER_ERROR.name())
            .path(request.getRequestURI())
            .method(request.getMethod())
            .timestamp(LocalDateTime.now())
            .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
