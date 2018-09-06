package com.clearlane.offersengine_job.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the quote_trade_in_vehicle database table.
 * 
 */
@Entity
@Table(name = "quote_trade_in_vehicle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuoteTradeInVehicle extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2416320063060115860L;

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

    @Column(name = "trade_in_value")
    private Double tradeInValue;

    @Column(name = "payoff_amount")
    private Double payoffAmount;

    @OneToOne(optional = true, mappedBy = "quoteTradeInVehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private QuoteVehicle quoteVehicle;

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

    public Double getTradeInValue() {
        return tradeInValue;
    }

    public void setTradeInValue(Double tradeInValue) {
        this.tradeInValue = tradeInValue;
    }

    public Double getPayoffAmount() {
        return payoffAmount;
    }

    public void setPayoffAmount(Double payoffAmount) {
        this.payoffAmount = payoffAmount;
    }

    public QuoteVehicle getQuoteVehicle() {
        return quoteVehicle;
    }

    public void setQuoteVehicle(QuoteVehicle quoteVehicle) {
        this.quoteVehicle = quoteVehicle;
    }

    //    @Override
    //    public String toString() {
    //        return "QuoteTradeInVehicle [vehicleYear=" + vehicleYear + ", vehicleMake=" + vehicleMake
    //                + ", vehicleModel=" + vehicleModel + ", vehicleTrim=" + vehicleTrim
    //                + ", vehicleMileage=" + vehicleMileage + ", tradeInValue=" + tradeInValue
    //                + ", payoffAmount=" + payoffAmount + "]";
    //    }

}
