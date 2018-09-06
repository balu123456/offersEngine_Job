package com.clearlane.offersengine_job.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Balakrishna Tirumalasetti
 *
 */

@ControllerAdvice
public class ClearlaneRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ClearlaneRestExceptionHandler.class);

    // 500 Internal server error
    @ExceptionHandler({ HttpClientErrorException.class, HttpServerErrorException.class })
    public void handleInternal(final RuntimeException ex, final WebRequest request)
            throws Http401UnAuthorizedException {
        LOG.error("Internal Error: {}", ex.getMessage());
        throw new Http401UnAuthorizedException(ex.getMessage());
    }

    // 503 Service Unavailable Exception
    @ExceptionHandler({ ServiceUnavailableException.class })
    protected ResponseEntity<Object> handleResourceAccessException(
            final ServiceUnavailableException ex, final WebRequest request) {
        LOG.error("Service Unavailable Exception: {}", ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),
                HttpStatus.SERVICE_UNAVAILABLE, request);
    }
}
