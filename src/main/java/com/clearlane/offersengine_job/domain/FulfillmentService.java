/**
 * 
 */
package com.clearlane.offersengine_job.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Sasikumar Velusamy
 *
 */
@Entity
@Table(name = "fulfillment_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FulfillmentService extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4111631843592003122L;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_description")
    private String serviceDescription;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FulfillmentService fulfillmentService = (FulfillmentService) o;
        if (fulfillmentService.getId() == null) {
            return false;
        }

        return Objects.equals(getId(), fulfillmentService.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FulfillmentService [id=" + getId() + ", serviceName=" + serviceName
                + ", serviceDescription=" + serviceDescription + "]";
    }

}
