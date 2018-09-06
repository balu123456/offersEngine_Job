package com.clearlane.offersengine_job.dto;

import com.clearlane.offersengine_job.domain.QuoteOfferFulfillment;

public class FulfillmentServiceResponse {

    private Boolean success;

    private Integer requestId;

    private QuoteOfferFulfillment quoteOfferFulfillment;

    private String serviceResponse = null;

    public String getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(String serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public QuoteOfferFulfillment getQuoteOfferFulfillment() {
        return quoteOfferFulfillment;
    }

    public void setQuoteOfferFulfillment(QuoteOfferFulfillment quoteOfferFulfillment) {
        this.quoteOfferFulfillment = quoteOfferFulfillment;
    }

}
