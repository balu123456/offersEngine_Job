/**
 * 
 */
package com.clearlane.offersengine_job.config;



import static com.clearlane.offersengine_job.OffersEngineJobConstants.ENV_DEV;
import static com.clearlane.offersengine_job.OffersEngineJobConstants.ENV_LOCAL;
import static com.clearlane.offersengine_job.OffersEngineJobConstants.ENV_PREPROD;
import static com.clearlane.offersengine_job.OffersEngineJobConstants.ENV_QA;
import static com.clearlane.offersengine_job.OffersEngineJobConstants.ENV_STG;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.client.RestTemplate;

import com.clearlane.offersengine_job.OffersEngineJobConstants;

/**
 * Configuration class that holds common beans which are required in application 
 * that need to initialized with some custom code.
 * 
 * @author sasikumar.velusamy
 */
@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class OffersEngineJobsConfig {

    public static final Logger LOG = LoggerFactory.getLogger(OffersEngineJobsConfig.class);

    @Autowired
    private Environment env;

 

    @Bean
    public Boolean isNonProd() {
        return env.acceptsProfiles(ENV_LOCAL, ENV_DEV, ENV_QA, ENV_PREPROD, ENV_STG);
    }

    @Bean
    public Boolean isEnvironmentLocal() {
        return env.acceptsProfiles(ENV_LOCAL);
    }

    

    /**
     * Create RestTemplate with SSL Context of TLS Version mentioned in the constant and returns it.
     * 
     * @return RestTemplate
     */
    @Bean(name = "secureHttpRestTemplate")
    public RestTemplate getRestTemplate() {
        SSLContext context;
        try {
            context = SSLContext.getInstance(OffersEngineJobConstants.TLS_VERSION);
            context.init(null, null, null);
            CloseableHttpClient httpClient = null;

            String proxyHost = System.getProperty("http.proxyHost");
            String proxyPort = System.getProperty("http.proxyPort");

            if (proxyHost != null && proxyPort != null) {
                httpClient = HttpClientBuilder.create().setSSLContext(context)
                        .setProxy(new HttpHost(proxyHost, Integer.parseInt(proxyPort), "http"))
                        .build();
                LOG.debug("Using proxy %s ", proxyHost + ":" + proxyPort);
            }
            else {
                httpClient = HttpClientBuilder.create().setSSLContext(context).build();
            }

            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
                    httpClient);
            return new RestTemplate(factory);
        }
        catch (Exception e) {
            LOG.error("Error while creating rest template: ", e);
        }
        return null;
    }

    /**
     * registers http rest template.
     * 
     * @return RestTemplate
     */
    @Bean(name = "httpRestTemplate")
    public RestTemplate gethttpRestTemplate() {

        try {
            return new RestTemplate();
        }
        catch (Exception e) {
            LOG.error("Error while creating rest template: ", e);
        }
        return null;

    }

}
