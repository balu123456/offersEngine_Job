/**
 * 
 */
package com.clearlane.offersengine_job.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

/**
 * @author Balakrishna Tirumalasetti
 *
 */

@Configuration
public class UrlRewriteFilterConfig {

    @Bean
    public FilterRegistrationBean urlRewritefilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UrlRewriteFilter());
        registration.setName("ByUrlRewriteFilter");
        registration.addInitParameter("confPath", "/WEB-INF/classes/urlrewrite.xml");
        registration.setOrder(0);
        return registration;
    }
   
}
