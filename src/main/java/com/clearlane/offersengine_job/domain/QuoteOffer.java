package com.clearlane.offersengine_job.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.clearlane.offersengine_job.enums.OfferStatus;
import com.clearlane.offersengine_job.enums.VehicleUsageType;
import com.clearlane.offersengine_job.web.util.Utilities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Quote Offer.
 */
@Entity
@Table(name = "quote_offer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuoteOffer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 937758795085368431L;

    @Column(name = "uuid")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "quote_request_id")
    private QuoteRequest quoteRequest;

    @ManyToOne
    @JoinColumn(name = "quote_vehicle_id")
    private QuoteVehicle quoteVehicle;

    @Column(name = "total_amount_requested")
    private Double totalAmountRequested;

    @Column(name = "max_amount_qualified")
    private Double maxAmountQualified;

    @Column(name = "term_in_months")
    private Integer termMonths;

    @Column(name = "apr")
    private Double apr;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "vsc_ref")
    private String vscRef;

    @Column(name = "gap_ref")
    private String gapRef;

    @Column(name = "mbi_ref")
    private String mbiRef;

    @Column(name = "approval_limits_ref")
    private Double approvalLimitsRef;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;

    @Column(name = "lender")
    private String lender;

    @Column(name = "monthly_payment_savings")
    private Double monthlyPaymentSavings;

    @Column(name = "total_interest_savings")
    private Double totalInterestSavings;

    @Enumerated(EnumType.STRING)
    @Column(name = "used_for")
    private VehicleUsageType usedFor;

    @Column(name = "offer_ref_url")
    private String offerRefUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OfferStatus status;

    @Column(name = "status_updated_on")
    private Date statusUpdatedOn;

    @Column(name = "expires_dt")
    private Date expiresDate;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public QuoteRequest getQuoteRequest() {
        return quoteRequest;
    }

    public void setQuoteRequest(QuoteRequest quoteRequest) {
        this.quoteRequest = quoteRequest;
    }

    public QuoteVehicle getQuoteVehicle() {
        return quoteVehicle;
    }

    public void setQuoteVehicle(QuoteVehicle quoteVehicle) {
        this.quoteVehicle = quoteVehicle;
    }

    public Double getTotalAmountRequested() {
        return totalAmountRequested;
    }

	public void setTotalAmountRequested(Double totalAmountRequested) {

		this.totalAmountRequested = (totalAmountRequested == null ? null : Utilities.roundOff(totalAmountRequested));
	}

    public Double getMaxAmountQualified() {
        return maxAmountQualified;
    }

    public void setMaxAmountQualified(Double maxAmountQualified) {
        this.maxAmountQualified = maxAmountQualified;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public Double getApr() {
        return apr;
    }

    public void setApr(Double apr) {
        this.apr = apr;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getVscRef() {
        return vscRef;
    }

    public void setVscRef(String vscRef) {
        this.vscRef = vscRef;
    }

    public String getGapRef() {
        return gapRef;
    }

    public void setGapRef(String gapRef) {
        this.gapRef = gapRef;
    }

    public String getMbiRef() {
        return mbiRef;
    }

    public void setMbiRef(String mbiRef) {
        this.mbiRef = mbiRef;
    }

    public Double getApprovalLimitsRef() {
        return approvalLimitsRef;
    }

    public void setApprovalLimitsRef(Double approvalLimitsRef) {
        this.approvalLimitsRef = approvalLimitsRef;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public Double getMonthlyPaymentSavings() {
        return monthlyPaymentSavings;
    }

    public void setMonthlyPaymentSavings(Double monthlyPaymentSavings) {
        this.monthlyPaymentSavings = monthlyPaymentSavings;
    }

    public Double getTotalInterestSavings() {
        return totalInterestSavings;
    }

    public void setTotalInterestSavings(Double totalInterestSavings) {
        this.totalInterestSavings = totalInterestSavings;
    }

    public VehicleUsageType getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(VehicleUsageType usedFor) {
        this.usedFor = usedFor;
    }

    public String getOfferRefUrl() {
        return offerRefUrl;
    }

    public void setOfferRefUrl(String offerRefUrl) {
        this.offerRefUrl = offerRefUrl;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
        setStatusUpdatedOn(Calendar.getInstance().getTime());
    }

    public Date getStatusUpdatedOn() {
        return statusUpdatedOn;
    }

    /**
     * This should be private. This should be invoked while updating status
     * 
     * @param statusUpdatedOn
     */
    private void setStatusUpdatedOn(Date statusUpdatedOn) {
        this.statusUpdatedOn = statusUpdatedOn;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuoteOffer quoteOffer = (QuoteOffer) o;
        if (quoteOffer.getId() == null) {
            return false;
        }

        return Objects.equals(getId(), quoteOffer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    //    @Override
    //    public String toString() {
    //        return "QuoteOffer [uuid=" + uuid + ", quoteRequest=" + quoteRequest
    //                + ", quoteVehicle=(do not use will result recursion )" + ", totalAmountRequested="
    //                + totalAmountRequested
    //                + ", maxAmountQualified=" + maxAmountQualified + ", termMonths=" + termMonths
    //                + ", apr=" + apr + ", interestRate=" + interestRate + ", vscRef=" + vscRef
    //                + ", gapRef=" + gapRef + ", mbiRef=" + mbiRef + ", approvalLimitsRef="
    //                + approvalLimitsRef + ", monthlyPayment=" + monthlyPayment + ", lender=" + lender
    //                + ", monthlyPaymentSavings=" + monthlyPaymentSavings + ", totalInterestSavings="
    //                + totalInterestSavings + ", usedFor=" + usedFor + ", offerRefUrl=" + offerRefUrl
    //                + ", status=" + status + ", statusUpdatedOn=" + statusUpdatedOn + ", expiresDate="
    //                + expiresDate + "]";
    //    }

}
