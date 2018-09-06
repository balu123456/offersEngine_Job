/**
 * 
 */
package com.clearlane.offersengine_job.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Vivek Nambiar
 *
 */
@Entity
@Table(name = "quote_request_miscellaneous_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuoteRequestMiscData extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 454576767677670L;

    @OneToOne
    @JoinColumn(name = "quote_request_id")
    private QuoteRequest quoteRequest;

    @Column(name = "destinationnamecode")
    private String destinationNameCode;

    @Column(name = "destinationdealerid")
    private String destinationDealerID;

    @Column(name = "destinationappnumber")
    private String destinationAppNumber;

    @Column(name = "manufacturerrebateamount")
    private Double manufacturerRebateAmount;

    @Column(name = "nettradeamount")
    private Double netTradeAmount;

    @Column(name = "othercharges")
    private Double otherCharges;

    @Column(name = "backendfees")
    private Double backendFees;

    @Column(name = "disabilitypremiumamount")
    private Double disabilityPremiumAmount;

    @Column(name = "creditlifepremiumamount")
    private Double creditLifePremiumAmount;

    @Column(name = "tradeinmodelyear")
    private Integer tradeInModelYear;

    @Column(name = "tradeinmake")
    private String tradeInMake;

    @Column(name = "tradeinmodel")
    private String tradeInModel;

    @Column(name = "tradeinpaymentamount")
    private Double tradeInPaymentAmount;

    @Column(name = "tradeinbalanceamount")
    private Double tradeInBalanceAmount;

    @Column(name = "tradeinfinancecompanyname")
    private String tradeInFinanceCompanyName;

    @Column(name = "tradeinnettradeallowanceamount")
    private Double tradeInNetTradeAllowanceAmount;

    @Column(name = "insurancetotalextendedwarrantyamount")
    private Double insuranceTotalExtendedWarrantyAmount;

    @Column(name = "gap")
    private Double gap;

    @Column(name = "tagandlicensefee")
    private Double tagAndLicenseFee;

    @Column(name = "downpayment")
    private Double downPayment;

    @Column(name = "totaltax")
    private Double totaltax;

    @Column(name = "salesprice")
    private Double salesPrice;

    @Column(name = "basepaymentamount")
    private Double basePaymentAmount;

    @Column(name = "salesclass")
    private String saleClass;

    @Column(name = "certifiedused")
    private String certifiedUsed;

    @Column(name = "passthroughsource")
    private String passthroughsource;

    @Column(name = "lenderprogram")
    private String lenderprogram;

    //EN-6499 - Add elements from Ally Clearpass Decision Inbound
    @Column(name = "applicant_id_analytics_score")
    private Integer applicantIdAnalyticsScore;

    @Column(name = "co_app_id_analytics_score")
    private Integer coAppIdAnalyticsScore;

    @Column(name = "portal_dealer_id")
    private String portalDealerId;

    @Column(name = "portal_user_id")
    private String portalUserId;

    public Double getBasePaymentAmount() {
        return basePaymentAmount;
    }

    public void setBasePaymentAmount(Double basePaymentAmount) {
        this.basePaymentAmount = basePaymentAmount;
    }

    public String getSaleClass() {
        return saleClass;
    }

    public void setSaleClass(String saleClass) {
        this.saleClass = saleClass;
    }

    public String getCertifiedUsed() {
        return certifiedUsed;
    }

    public void setCertifiedUsed(String certifiedUsed) {
        this.certifiedUsed = certifiedUsed;
    }

    public Double getInsuranceTotalExtendedWarrantyAmount() {
        return insuranceTotalExtendedWarrantyAmount;
    }

    public void setInsuranceTotalExtendedWarrantyAmount(
            Double insuranceTotalExtendedWarrantyAmount) {
        this.insuranceTotalExtendedWarrantyAmount = insuranceTotalExtendedWarrantyAmount;
    }

    public Double getGap() {
        return gap;
    }

    public void setGap(Double gap) {
        this.gap = gap;
    }

    public Double getTagAndLicenseFee() {
        return tagAndLicenseFee;
    }

    public void setTagAndLicenseFee(Double tagAndLicenseFee) {
        this.tagAndLicenseFee = tagAndLicenseFee;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public Double getTotaltax() {
        return totaltax;
    }

    public void setTotaltax(Double totaltax) {
        this.totaltax = totaltax;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getDestinationNameCode() {
        return destinationNameCode;
    }

    public void setDestinationNameCode(String destinationNameCode) {
        this.destinationNameCode = destinationNameCode;
    }

    public String getDestinationDealerID() {
        return destinationDealerID;
    }

    public void setDestinationDealerID(String destinationDealerID) {
        this.destinationDealerID = destinationDealerID;
    }

    public String getDestinationAppNumber() {
        return destinationAppNumber;
    }

    public void setDestinationAppNumber(String destinationAppNumber) {
        this.destinationAppNumber = destinationAppNumber;
    }

    public Double getManufacturerRebateAmount() {
        return manufacturerRebateAmount;
    }

    public void setManufacturerRebateAmount(Double manufacturerRebateAmount) {
        this.manufacturerRebateAmount = manufacturerRebateAmount;
    }

    public Double getNetTradeAmount() {
        return netTradeAmount;
    }

    public void setNetTradeAmount(Double netTradeAmount) {
        this.netTradeAmount = netTradeAmount;
    }

    public Double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Double getBackendFees() {
        return backendFees;
    }

    public void setBackendFees(Double backendFees) {
        this.backendFees = backendFees;
    }

    public Double getDisabilityPremiumAmount() {
        return disabilityPremiumAmount;
    }

    public void setDisabilityPremiumAmount(Double disabilityPremiumAmount) {
        this.disabilityPremiumAmount = disabilityPremiumAmount;
    }

    public Double getCreditLifePremiumAmount() {
        return creditLifePremiumAmount;
    }

    public void setCreditLifePremiumAmount(Double creditLifePremiumAmount) {
        this.creditLifePremiumAmount = creditLifePremiumAmount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getTradeInModelYear() {
        return tradeInModelYear;
    }

    public void setTradeInModelYear(Integer tradeInModelYear) {
        this.tradeInModelYear = tradeInModelYear;
    }

    public String getTradeInMake() {
        return tradeInMake;
    }

    public void setTradeInMake(String tradeInMake) {
        this.tradeInMake = tradeInMake;
    }

    public String getTradeInModel() {
        return tradeInModel;
    }

    public void setTradeInModel(String tradeInModel) {
        this.tradeInModel = tradeInModel;
    }

    public Double getTradeInPaymentAmount() {
        return tradeInPaymentAmount;
    }

    public void setTradeInPaymentAmount(Double tradeInPaymentAmount) {
        this.tradeInPaymentAmount = tradeInPaymentAmount;
    }

    public Double getTradeInBalanceAmount() {
        return tradeInBalanceAmount;
    }

    public void setTradeInBalanceAmount(Double tradeInBalanceAmount) {
        this.tradeInBalanceAmount = tradeInBalanceAmount;
    }

    public String getTradeInFinanceCompanyName() {
        return tradeInFinanceCompanyName;
    }

    public void setTradeInFinanceCompanyName(String tradeInFinanceCompanyName) {
        this.tradeInFinanceCompanyName = tradeInFinanceCompanyName;
    }

    public Double getTradeInNetTradeAllowanceAmount() {
        return tradeInNetTradeAllowanceAmount;
    }

    public void setTradeInNetTradeAllowanceAmount(Double tradeInNetTradeAllowanceAmount) {
        this.tradeInNetTradeAllowanceAmount = tradeInNetTradeAllowanceAmount;
    }

    public QuoteRequest getQuoteRequest() {
        return quoteRequest;
    }

    public void setQuoteRequest(QuoteRequest quoteRequest) {
        this.quoteRequest = quoteRequest;
    }

    public String getPassthroughsource() {
        return passthroughsource;
    }

    public void setPassthroughsource(String passthroughsource) {
        this.passthroughsource = passthroughsource;
    }

    public String getLenderprogram() {
        return lenderprogram;
    }

    public void setLenderprogram(String lenderprogram) {
        this.lenderprogram = lenderprogram;
    }

    public Integer getApplicantIdAnalyticsScore() {
        return applicantIdAnalyticsScore;
    }

    public void setApplicantIdAnalyticsScore(Integer applicantIdAnalyticsScore) {
        this.applicantIdAnalyticsScore = applicantIdAnalyticsScore;
    }

    public Integer getCoAppIdAnalyticsScore() {
        return coAppIdAnalyticsScore;
    }

    public void setCoAppIdAnalyticsScore(Integer coAppIdAnalyticsScore) {
        this.coAppIdAnalyticsScore = coAppIdAnalyticsScore;
    }

    public String getPortalDealerId() {
        return portalDealerId;
    }

    public void setPortalDealerId(String portalDealerId) {
        this.portalDealerId = portalDealerId;
    }

    public String getPortalUserId() {
        return portalUserId;
    }

    public void setPortalUserId(String portalUserId) {
        this.portalUserId = portalUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuoteRequestMiscData quoteRequestMiscData = (QuoteRequestMiscData) o;
        if (quoteRequestMiscData.getId() == null) {
            return false;
        }

        return Objects.equals(getId(), quoteRequestMiscData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuoteRequestMiscData [quoteRequest=" + quoteRequest + ", destinationNameCode="
                + destinationNameCode + ", destinationDealerID=" + destinationDealerID
                + ", destinationAppNumber=" + destinationAppNumber + ", manufacturerRebateAmount="
                + manufacturerRebateAmount + ", netTradeAmount=" + netTradeAmount
                + ", otherCharges=" + otherCharges + ", backendFees=" + backendFees
                + ", disabilityPremiumAmount=" + disabilityPremiumAmount
                + ", creditLifePremiumAmount=" + creditLifePremiumAmount + ", tradeInModelYear="
                + tradeInModelYear + ", tradeInMake=" + tradeInMake + ", tradeInModel="
                + tradeInModel + ", tradeInPaymentAmount=" + tradeInPaymentAmount
                + ", tradeInBalanceAmount=" + tradeInBalanceAmount + ", tradeInFinanceCompanyName="
                + tradeInFinanceCompanyName + ", tradeInNetTradeAllowanceAmount="
                + tradeInNetTradeAllowanceAmount + ", insuranceTotalExtendedWarrantyAmount="
                + insuranceTotalExtendedWarrantyAmount + ", gap=" + gap + ", tagAndLicenseFee="
                + tagAndLicenseFee + ", downPayment=" + downPayment + ", totaltax=" + totaltax
                + ", salesPrice=" + salesPrice + ", basePaymentAmount=" + basePaymentAmount
                + ", saleClass=" + saleClass + ", certifiedUsed=" + certifiedUsed
                + ", passthroughsource=" + passthroughsource + ", lenderprogram=" + lenderprogram
                + ", applicantIdAnalyticsScore=" + applicantIdAnalyticsScore
                + ", coAppIdAnalyticsScore=" + coAppIdAnalyticsScore + ", portalDealerId="
                + portalDealerId + ", portalUserId=" + portalUserId + "]";
    }

}
