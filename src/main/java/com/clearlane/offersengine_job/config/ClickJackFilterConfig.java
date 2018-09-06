package com.clearlane.offersengine_job.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Balakrishna Tirumalasetti
 *
 */

@Configuration
public class ClickJackFilterConfig {

    @Bean
    public FilterRegistrationBean clickJackFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ClickjackFilter());
        registration.setName("ClickjackFilter");
        registration.setOrder(2);
        return registration;
    }

}
