/**
 * 
 */
package com.clearlane.offersengine_job.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.clearlane.offersengine_job.enums.QuoteOfferFulfillmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Sasikumar Velusamy
 *
 */
@Entity
@Table(name = "quote_offer_fulfillment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuoteOfferFulfillment implements Serializable {

    private static final long serialVersionUID = 5558680061160105100L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = true, updatable = false)
    private Long id;

    @Column(name = "created_dt")
    private Date createdDate;

    @Column(name = "last_updated_dt")
    private Date lastUpdatedDate;

    @Column(name = "quote_id")
    private Long quoteId;

    @ManyToOne
    @JoinColumn(name = "quote_offer_id")
    private QuoteOffer quoteOffer;

    @Column(name = "lender_name")
    private String lenderName;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private FulfillmentService service;

    @Column(name = "service_reference_num")
    private String serviceReferenceNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QuoteOfferFulfillmentStatus status;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "miscellaneous_data")
    private String miscellaneousData;

    public String getMiscellaneousData() {
        return miscellaneousData;
    }

    public void setMiscellaneousData(String miscellaneousData) {
        this.miscellaneousData = miscellaneousData;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public QuoteOffer getQuoteOffer() {
        return quoteOffer;
    }

    public void setQuoteOffer(QuoteOffer quoteOffer) {
        this.quoteOffer = quoteOffer;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public FulfillmentService getService() {
        return service;
    }

    public void setService(FulfillmentService service) {
        this.service = service;
    }

    public String getServiceReferenceNum() {
        return serviceReferenceNum;
    }

    public void setServiceReferenceNum(String serviceReferenceNum) {
        this.serviceReferenceNum = serviceReferenceNum;
    }

    public QuoteOfferFulfillmentStatus getStatus() {
        return status;
    }

    public void setStatus(QuoteOfferFulfillmentStatus status) {
        this.status = status;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuoteOfferFulfillment quoteOfferFulfillment = (QuoteOfferFulfillment) o;
        if (quoteOfferFulfillment.getId() == null) {
            return false;
        }

        return Objects.equals(getId(), quoteOfferFulfillment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuoteOfferFulfillment [quoteId=" + quoteId + ", quoteOffer=" + quoteOffer
                + ", lenderId=" + lenderName + ", service=" + service + ", serviceReferenceNum="
                + serviceReferenceNum + ", status=" + status + ", retryCount=" + retryCount + "]";
    }

}
