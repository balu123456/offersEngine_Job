/**
 * 
 */
package com.clearlane.offersengine_job.web.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Balakrishna Tirumalasetti
 *
 */

public class ServiceUnavailableException extends RuntimeException {

    private static final long serialVersionUID = 2878011390013171589L;

    private HttpStatus statusCode;

    private String body;

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(HttpStatus statusCode, String body, String message) {
        super(message);
        this.statusCode = statusCode;
        this.body = body;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
