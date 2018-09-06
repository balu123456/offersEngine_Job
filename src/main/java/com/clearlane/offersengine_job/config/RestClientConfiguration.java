/**
 * 
 */
package com.clearlane.offersengine_job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.clearlane.offersengine_job.web.exception.OffersengineJobResponseErrorHandler;

/**
 * @author Balakrishna Tirumalasetti
 *
 */

@Configuration
public class RestClientConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new OffersengineJobResponseErrorHandler());
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        return restTemplate;
    }

}
