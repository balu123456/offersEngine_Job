/**
 * 
 */
package com.clearlane.offersengine_job.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sasikumar.velusamy
 *
 */
@Entity
@Table(name = "applicant_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicantAddress extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2084886693340518610L;

    @ManyToOne
    @JoinColumn(name = "quote_applicant_id")
    private QuoteApplicant quoteApplicant;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private ApplicantAddressType addressType;

    @Column(name = "address_line1", length = 255)
    private String addressLine1;

    @Column(name = "address_line2", length = 255)
    private String addressLine2;

    @Column(name = "address_city", length = 50)
    private String addressCity;

    @Column(name = "address_state", length = 50)
    private String addressState;

    @Column(name = "address_zip", length = 10)
    private String addressZip;

    @Column(name = "start_date_at_residence")
    private String startDateAtResidence;

    @Column(name = "end_date_at_residence")
    private String endDateAtResidence;

    @Column(name = "years_at_residence")
    private Integer yearsAtResidence;

    @Column(name = "months_at_residence")
    private Integer monthsAtResidence;

    public QuoteApplicant getQuoteApplicant() {
        return quoteApplicant;
    }

    public void setQuoteApplicant(QuoteApplicant quoteApplicant) {
        this.quoteApplicant = quoteApplicant;
    }

    public ApplicantAddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(ApplicantAddressType addressType) {
        this.addressType = addressType;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getStartDateAtResidence() {
        return startDateAtResidence;
    }

    public void setStartDateAtResidence(String startDateAtResidence) {
        this.startDateAtResidence = startDateAtResidence;
    }

    public String getEndDateAtResidence() {
        return endDateAtResidence;
    }

    public void setEndDateAtResidence(String endDateAtResidence) {
        this.endDateAtResidence = endDateAtResidence;
    }

    public Integer getYearsAtResidence() {
        return yearsAtResidence;
    }

    public void setYearsAtResidence(Integer yearsAtResidence) {
        this.yearsAtResidence = yearsAtResidence;
    }

    public Integer getMonthsAtResidence() {
        return monthsAtResidence;
    }

    public void setMonthsAtResidence(Integer monthsAtResidence) {
        this.monthsAtResidence = monthsAtResidence;
    }

    @Override
    public String toString() {
        return "ApplicantAddress [quoteApplicant=" + quoteApplicant + ", addressType=" + addressType
                + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
                + ", addressCity=" + addressCity + ", addressState=" + addressState
                + ", addressZip=" + addressZip + ", startDateAtResidence=" + startDateAtResidence
                + ", endDateAtResidence=" + endDateAtResidence + ", yearsAtResidence="
                + yearsAtResidence + ", monthsAtResidence=" + monthsAtResidence + "]";
    }

}
