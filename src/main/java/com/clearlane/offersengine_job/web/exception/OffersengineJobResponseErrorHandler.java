package com.clearlane.offersengine_job.web.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.clearlane.offersengine_job.web.util.RestUtil;

/**
 * @author Balakrishna Tirumalasetti
 *
 */

public class OffersengineJobResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger LOG = LoggerFactory
            .getLogger(OffersengineJobResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
            LOG.error("Service Unavilable");
            throw new ServiceUnavailableException(HttpStatus.EXPECTATION_FAILED,
                    response.getStatusText(),
                    "{\"message\" : \"Connection refused; unable to reach servers\"}");
        }
    }

}
