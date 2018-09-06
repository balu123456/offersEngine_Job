/**
 * 
 */
package com.clearlane.offersengine_job.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.clearlane.offersengine_job.properties.OffersEngineJobProperties;

/**
 * @author Balakrishna Tirumalasetti
 *
 */
@Configuration
public class CorsConfig {

    @Inject
    private OffersEngineJobProperties props;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = props.getCors();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            source.registerCorsConfiguration("/api/**", config);
        }
        return new CorsFilter(source);
    }

}
