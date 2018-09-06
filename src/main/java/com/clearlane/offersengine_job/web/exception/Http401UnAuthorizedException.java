/**
 * 
 */
package com.clearlane.offersengine_job.web.exception;

/**
 * @author Balakrishna Tirumalasetti
 *
 */
public class Http401UnAuthorizedException extends Exception {

    private static final long serialVersionUID = 5796680980302983486L;

    public Http401UnAuthorizedException() {
    }

    public Http401UnAuthorizedException(String message) {
        super(message);
    }

}
