package com.clearlane.offersengine_job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Initializes the servlet based on the profile selected. 
 * if no profile selected uses the local profile as default. 
 */
@Configuration
public class OffersEngineJobServletInitializer extends SpringBootServletInitializer {

    private final Logger log = LoggerFactory.getLogger(OffersEngineJobServletInitializer.class);

    /* (non-Javadoc)
     * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.profiles(addDefaultProfile()).sources(OffersEngineJobApplication.class);
    }

    /**
     * Set a default profile if it has not been set.
     * <p>
     * Need to add following JVM setting based on the environment 
     * -Dspring.profiles.active=dev
     * </p>
     */
    private String addDefaultProfile() {
        String profile = System.getProperty(OffersEngineJobConstants.SPRING_PROFILES_ACTIVE);
        if (profile != null) {
            log.info("Running with Spring profile(s) : {}", profile);
            return profile;
        }

        log.warn("No Spring profile configured, running with default configuration");
        return OffersEngineJobConstants.ENV_LOCAL;
    }
}