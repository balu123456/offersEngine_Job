/**
 * 
 */
package com.clearlane.offersengine_job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;

/**
 * @author sasikumar.velusamy
 *
 */
@Configuration
public class MetricsConfiguration {

    private MetricRegistry metricRegistry = new MetricRegistry();

    @Bean
    public MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }
}
