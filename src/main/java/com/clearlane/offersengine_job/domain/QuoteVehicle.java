package com.clearlane.offersengine_job.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.clearlane.offersengine_job.enums.VehicleUsageType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the quote_vehicle database table.
 * 
 */
@Entity
@Table(name = "quote_vehicle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuoteVehicle extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2164002452492437121L;

    @Column(name = "nada_id")
    private String nadaId;

    @Column(name = "chrome_id")
    private String chromeId;

    @Column(name = "kbb_id")
    private String kbbId;

    @Column(name = "vehicle_vin")
    private String vehicleVin;

    @Column(name = "vehicle_year")
    private Integer vehicleYear;

    @Column(name = "vehicle_make")
    private String vehicleMake;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "vehicle_trim")
    private String vehicleTrim;

    @Column(name = "vehicle_mileage")
    private Integer vehicleMileage;

    @Column(name = "vehicle_condition")
    private String vehicleCondition;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @Column(name = "term_in_months")
    private Short termMonths;

    @Column(name = "vehicle_value")
    private Double vehicleValue;

    @Column(name = "msrp_value")
    private Double msrpValue;

    @Column(name = "trade_value")
    private Double tradeValue;

    @Column(name = "retail_value")
    private Double retailvalue;

    @Column(name = "estimated_taxes_and_fees")
    private Double estimatedTaxesAndFees;

    @Column(name = "gap_price_incl_taxes")
    private Double gapPriceInclTaxes;

    @Column(name = "service_contract_incl_taxes")
    private Double serviceContractTotalInclTaxes;

    @Column(name = "other_charges")
    private Double otherCharges;

    @Column(name = "total_rebates_and_discounts")
    private Double totalRebatesAndDiscounts;

    @Column(name = "trade_in_vehicle_payoff")
    private Double tradeInVehiclePayoff;

    @Column(name = "down_payment")
    private Double downPayment;

    @Enumerated(EnumType.STRING)
    @Column(name = "used_for")
    private VehicleUsageType usedFor;

    @OneToOne
    @JoinColumn(name = "quote_offer_id")
    private QuoteOffer quoteOffer;

    @Column(name = "book_value_source")
    private String bookValueSource;

    @Column(name = "sent_approval_to_dealer")
    private Boolean sentApprovalToDealer;

    @Column(name = "stock_number")
    private String stockNumber;
    
    @Column(name = "invoice_amount")
    private Double invoiceAmount;

    //bi-directional many-to-one association to QuoteRequest
    @ManyToOne
    @JoinColumn(name = "quote_request_id")
    private QuoteRequest quoteRequest;

    @OneToOne(optional = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "quote_trade_in_vehicle_id")
    private QuoteTradeInVehicle quoteTradeInVehicle;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "provider_dealer_id")
    private String providerDealerId;

    @Column(name = "cancel_interest", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean cancelInterest;

    @Transient
    private Boolean vehicleAvailable;

    public String getNadaId() {
        return nadaId;
    }

    public void setNadaId(String nadaId) {
        this.nadaId = nadaId;
    }

    public String getChromeId() {
        return chromeId;
    }

    public void setChromeId(String chromeId) {
        this.chromeId = chromeId;
    }

    public String getKbbId() {
        return kbbId;
    }

    public void setKbbId(String kbbId) {
        this.kbbId = kbbId;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleTrim() {
        return vehicleTrim;
    }

    public void setVehicleTrim(String vehicleTrim) {
        this.vehicleTrim = vehicleTrim;
    }

    public Integer getVehicleMileage() {
        return vehicleMileage;
    }

    public void setVehicleMileage(Integer vehicleMileage) {
        this.vehicleMileage = vehicleMileage;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Short getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Short termMonths) {
        this.termMonths = termMonths;
    }

    public Double getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(Double vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public Double getMsrpValue() {
        return msrpValue;
    }

    public void setMsrpValue(Double msrpValue) {
        this.msrpValue = msrpValue;
    }

    public Double getTradeValue() {
        return tradeValue;
    }

    public void setTradeValue(Double tradeValue) {
        this.tradeValue = tradeValue;
    }

    public Double getRetailvalue() {
        return retailvalue;
    }

    public void setRetailvalue(Double retailvalue) {
        this.retailvalue = retailvalue;
    }

    public Double getEstimatedTaxesAndFees() {
        return estimatedTaxesAndFees;
    }

    public void setEstimatedTaxesAndFees(Double estimatedTaxesAndFees) {
        this.estimatedTaxesAndFees = estimatedTaxesAndFees;
    }

    public Double getGapPriceInclTaxes() {
        return gapPriceInclTaxes;
    }

    public void setGapPriceInclTaxes(Double gapPriceInclTaxes) {
        this.gapPriceInclTaxes = gapPriceInclTaxes;
    }

    public Double getServiceContractTotalInclTaxes() {
        return serviceContractTotalInclTaxes;
    }

    public void setServiceContractTotalInclTaxes(Double serviceContractTotalInclTaxes) {
        this.serviceContractTotalInclTaxes = serviceContractTotalInclTaxes;
    }

    public Double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Double getTotalRebatesAndDiscounts() {
        return totalRebatesAndDiscounts;
    }

    public void setTotalRebatesAndDiscounts(Double totalRebatesAndDiscounts) {
        this.totalRebatesAndDiscounts = totalRebatesAndDiscounts;
    }

    public Double getTradeInVehiclePayoff() {
        return tradeInVehiclePayoff;
    }

    public void setTradeInVehiclePayoff(Double tradeInVehiclePayoff) {
        this.tradeInVehiclePayoff = tradeInVehiclePayoff;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public VehicleUsageType getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(VehicleUsageType usedFor) {
        this.usedFor = usedFor;
    }

    public QuoteRequest getQuoteRequest() {
        return quoteRequest;
    }

    public void setQuoteRequest(QuoteRequest quoteRequest) {
        this.quoteRequest = quoteRequest;
    }

    public String getBookValueSource() {
        return bookValueSource;
    }

    public void setBookValueSource(String bookValueSource) {
        this.bookValueSource = bookValueSource;
    }

    public Boolean getSentApprovalToDealer() {
        return sentApprovalToDealer;
    }

    public void setSentApprovalToDealer(Boolean sentApprovalToDealer) {
        this.sentApprovalToDealer = sentApprovalToDealer;
    }

    public QuoteTradeInVehicle getQuoteTradeInVehicle() {
        return quoteTradeInVehicle;
    }

    public void setQuoteTradeInVehicle(QuoteTradeInVehicle quoteTradeInVehicle) {
        this.quoteTradeInVehicle = quoteTradeInVehicle;
    }

    public QuoteOffer getQuoteOffer() {
        return quoteOffer;
    }

    public void setQuoteOffer(QuoteOffer quoteOffer) {
        this.quoteOffer = quoteOffer;
    }

    public Boolean getCancelInterest() {
        return cancelInterest;
    }

    public void setCancelInterest(Boolean cancelInterest) {
        this.cancelInterest = cancelInterest;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderDealerId() {
        return providerDealerId;
    }

    public void setProviderDealerId(String providerDealerId) {
        this.providerDealerId = providerDealerId;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }
    
    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Boolean getVehicleAvailable() {
        return vehicleAvailable;
    }

    public void setVehicleAvailable(Boolean vehicleAvailable) {
        this.vehicleAvailable = vehicleAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuoteVehicle quoteVehicle = (QuoteVehicle) o;
        if (quoteVehicle.getId() == null) {
            return false;
        }

        return Objects.equals(getId(), quoteVehicle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    //    @Override
    //    public String toString() {
    //        StringBuilder builder = new StringBuilder();
    //        builder.append("QuoteVehicle [nadaId=");
    //        builder.append(nadaId);
    //        builder.append(", chromeId=");
    //        builder.append(chromeId);
    //        builder.append(", kbbId=");
    //        builder.append(kbbId);
    //        builder.append(", vehicleVin=");
    //        builder.append(vehicleVin);
    //        builder.append(", vehicleYear=");
    //        builder.append(vehicleYear);
    //        builder.append(", vehicleMake=");
    //        builder.append(vehicleMake);
    //        builder.append(", vehicleModel=");
    //        builder.append(vehicleModel);
    //        builder.append(", vehicleTrim=");
    //        builder.append(vehicleTrim);
    //        builder.append(", vehicleMileage=");
    //        builder.append(vehicleMileage);
    //        builder.append(", vehicleCondition=");
    //        builder.append(vehicleCondition);
    //        builder.append(", vehicleValue=");
    //        builder.append(vehicleValue);
    //        builder.append(", msrpValue=");
    //        builder.append(msrpValue);
    //        builder.append(", tradeValue=");
    //        builder.append(tradeValue);
    //        builder.append(", retailvalue=");
    //        builder.append(retailvalue);
    //        builder.append(", estimatedTaxesAndFees=");
    //        builder.append(estimatedTaxesAndFees);
    //        builder.append(", gapPriceInclTaxes=");
    //        builder.append(gapPriceInclTaxes);
    //        builder.append(", serviceContractTotalInclTaxes=");
    //        builder.append(serviceContractTotalInclTaxes);
    //        builder.append(", otherCharges=");
    //        builder.append(otherCharges);
    //        builder.append(", totalRebatesAndDiscounts=");
    //        builder.append(totalRebatesAndDiscounts);
    //        builder.append(", tradeInVehiclePayoff=");
    //        builder.append(tradeInVehiclePayoff);
    //        builder.append(", downPayment=");
    //        builder.append(downPayment);
    //        builder.append(", usedFor=");
    //        builder.append(usedFor);
    //        builder.append(", quoteOffer=");
    //        builder.append(quoteOffer);
    //        builder.append(", bookValueSource=");
    //        builder.append(bookValueSource);
    //        builder.append(", sentApprovalToDealer=");
    //        builder.append(sentApprovalToDealer);
    //        builder.append(", quoteRequest=");
    //        builder.append(quoteRequest);
    //        builder.append(", quoteTradeInVehicle=");
    //        builder.append(quoteTradeInVehicle);
    //        builder.append(", quoteVehicleDealer=");
    //        builder.append(quoteVehicleDealer);
    //        builder.append(", cancelInterest=");
    //        builder.append(cancelInterest);
    //        builder.append("]");
    //        return builder.toString();
    //    }

}