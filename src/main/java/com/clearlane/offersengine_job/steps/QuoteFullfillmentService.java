package com.clearlane.offersengine_job.steps;

import java.util.List;

import com.clearlane.offersengine_job.domain.QuoteOfferFulfillment;
import com.clearlane.offersengine_job.dto.FulfillmentServiceResponse;
import com.clearlane.offersengine_job.properties.OffersEngineJobProperties;
import com.clearlane.offersengine_job.requestprocessor.BluelinkFulfillmentRequestProcessor;


public class QuoteFullfillmentService {

    private OffersEngineJobProperties offersEngineJobProperties;
    private BluelinkFulfillmentRequestProcessor bluelinkFulfillmentRequestProcessor;

    
    public QuoteFullfillmentService(OffersEngineJobProperties offersEngineJobProperties,
            BluelinkFulfillmentRequestProcessor bluelinkFulfillmentRequestProcessor) {
        this.offersEngineJobProperties = offersEngineJobProperties;
        this.bluelinkFulfillmentRequestProcessor = bluelinkFulfillmentRequestProcessor;
    }

    public FulfillmentServiceResponse processService(QuoteOfferFulfillment fulfillment) {
        FulfillmentServiceResponse fulfillmentServiceResponse = new FulfillmentServiceResponse();
        List<String> serviceVariables = offersEngineJobProperties.getFulfillmentServices()
                .get(fulfillment.getService().getServiceName());

        if (serviceVariables != null && !serviceVariables.isEmpty()) {
            String destinationUrl = serviceVariables.get(0);
            String authKeyHeader = serviceVariables.get(1);
            String authKeyValue = serviceVariables.get(2);

            if ("BlueOps".equalsIgnoreCase(fulfillment.getService().getServiceName())) {
                System.out.println("QuoteFullfillmentService.processService() ==> in blueops ");
            }
            else if ("BlueLink".equalsIgnoreCase(fulfillment.getService().getServiceName())) {
                System.out.println("QuoteFullfillmentService.processService() ==> in bluelink");
                fulfillmentServiceResponse = bluelinkFulfillmentRequestProcessor
                        .processRequest(destinationUrl, fulfillment,
                        authKeyHeader, authKeyValue);

            }

        }

        return fulfillmentServiceResponse;

    }

}
