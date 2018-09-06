package com.clearlane.offersengine_job.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Quote Origination.
 */
@Entity
@Table(name = "quote_origination")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuoteOrigination extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 937758795085368431L;

    @NotNull
    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "request")
    private String request;

    @Column(name = "status")
    private String status;

    @Column(name = "response")
    private String response;

    @Column(name = "leadsource_partner_id")
    private Long leadSourcePartnerId;

    @Column(name = "leadsource_partnerclient_id")
    private Long leadSourcePartnerClientId;

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getLeadSourcePartnerId() {
        return leadSourcePartnerId;
    }

    public void setLeadSourcePartnerId(Long leadSourcePartnerId) {
        this.leadSourcePartnerId = leadSourcePartnerId;
    }

    public Long getLeadSourcePartnerClientId() {
        return leadSourcePartnerClientId;
    }

    public void setLeadSourcePartnerClientId(Long leadSourcePartnerClientId) {
        this.leadSourcePartnerClientId = leadSourcePartnerClientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuoteOrigination quoteOrigination = (QuoteOrigination) o;
        if (quoteOrigination.getId() == null) {
            return false;
        }

        return Objects.equals(getId(), quoteOrigination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuoteOrigination [id=" + getId() + ", partnerId=" + partnerId + ", requestJson="
                + request + ", status=" + status + ", responseJson=" + response + ", createdDate="
                + getCreatedDate() + "]";
    }

}
