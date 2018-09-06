/**
 *
 */
package com.clearlane.offersengine_job.properties;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author Balakrishna Tirumalasetti
 *
 */

@ConfigurationProperties(prefix = "offersengine_job", ignoreUnknownFields = false)
public class OffersEngineJobProperties {

    private String csAPIAccessAuthKeyName;
    private String csAPIAccessAuthKeyValue;

    private String downloadInstructionalText; //config property for disabling popup based on env
    private Cache cache = new Cache();

    
    private final CorsConfiguration cors = new CorsConfiguration();

    private Map<String, List<String>> fulfillmentServices = null;

    public String getCsAPIAccessAuthKeyName() {
        return csAPIAccessAuthKeyName;
    }

    public void setCsAPIAccessAuthKeyName(String csAPIAccessAuthKeyName) {
        this.csAPIAccessAuthKeyName = csAPIAccessAuthKeyName;
    }

    public String getCsAPIAccessAuthKeyValue() {
        return csAPIAccessAuthKeyValue;
    }

    public void setCsAPIAccessAuthKeyValue(String csAPIAccessAuthKeyValue) {
        this.csAPIAccessAuthKeyValue = csAPIAccessAuthKeyValue;
    }

    public String getDownloadInstructionalText() {
        return downloadInstructionalText;
    }

    public void setDownloadInstructionalText(String downloadInstructionalText) {
        this.downloadInstructionalText = downloadInstructionalText;
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public static class Cache {
        private Elastic elastic = new Elastic();

        public Elastic getElastic() {
            return elastic;
        }

        public void setElastic(Elastic elastic) {
            this.elastic = elastic;
        }

        private int timeToLiveSeconds = 3600;

        private final Ehcache ehcache = new Ehcache();

        public int getTimeToLiveSeconds() {
            return timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(int timeToLiveSeconds) {
            this.timeToLiveSeconds = timeToLiveSeconds;
        }

        public Ehcache getEhcache() {
            return ehcache;
        }

        public static class Ehcache {

            private String maxBytesLocalHeap = "16M";

            public String getMaxBytesLocalHeap() {
                return maxBytesLocalHeap;
            }

            public void setMaxBytesLocalHeap(String maxBytesLocalHeap) {
                this.maxBytesLocalHeap = maxBytesLocalHeap;
            }
        }

    }

    public static class Elastic {
        private End end = new End();

        public End getEnd() {
            return end;
        }

        public void setEnd(End end) {
            this.end = end;
        }

    }

    public static class End {
        private String point;

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Map<String, List<String>> getFulfillmentServices() {
        return fulfillmentServices;
    }

    public void setFulfillmentServices(Map<String, List<String>> fulfillmentServices) {
        this.fulfillmentServices = fulfillmentServices;
    }
    

    

   

}
