/**
 * 
 */
package com.clearlane.offersengine_job.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author sasikumar.velusamy
 *
 */
@Configuration
public class StaticKeyValidationFilterConfig {

    @Autowired
    private StaticKeyValidationFilter staticKeyValidationFilter;

    @Bean
    public FilterRegistrationBean staticAuthKeyFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(staticKeyValidationFilter);
        registration.setName("StaticAuthKeyFilter");
        registration.addUrlPatterns("/api/*");
        registration.setOrder(1);
        return registration;
    }

}
