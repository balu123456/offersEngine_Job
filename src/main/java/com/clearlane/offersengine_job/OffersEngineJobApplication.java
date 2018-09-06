package com.clearlane.offersengine_job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.clearlane.offersengine_job.properties.OffersEngineJobProperties;

/**
 * OffersEngineJobApplication
 *
 */
@SpringBootApplication(scanBasePackages = "com.clearlane.offersengine_job.config, com.clearlane.offersengine_job.web.rest, com.clearlane.offersengine_job.domain, com.clearlane.offersengine_job.dataservice, com.clearlane.offersengine_job.batchconfiguration, com.clearlane.offersengine_job.steps")
@EnableAutoConfiguration
@EnableConfigurationProperties(OffersEngineJobProperties.class)
@EnableBatchProcessing
@EnableScheduling
public class OffersEngineJobApplication
{
    public static void main(String[] args) {
        SpringApplication.run(OffersEngineJobApplication.class, args);
    }

}
