package com.clearlane.offersengine_job.requestprocessor;

import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.clearlane.offersengine_job.domain.QuoteOfferFulfillment;
import com.clearlane.offersengine_job.dto.Application;
import com.clearlane.offersengine_job.dto.FulfillmentServiceResponse;
import com.clearlane.offersengine_job.service.OffersEngineDataService;


public class BluelinkFulfillmentRequestProcessor {

    private Logger LOG = LoggerFactory.getLogger(BluelinkFulfillmentRequestProcessor.class);
    private static final String APPLICATION_SOURCE_NAME = "OffersEngine";
    private static final String blueops_bluelink_loanCategory = "Auto";
    private static final String blueops_capone_requestedPaymentMethodToHardcode = "Automatic";
    private static final String blueops_bluelink_packageDeliveryType = "Home";
    private static JAXBContext contextObj = null;
    private static final String SUCCESS_RESPONSE = "Successful Post";


    private RestTemplate restTemplate;
    private OffersEngineDataService offersEngineDataService;

    static {
        try {
            contextObj = JAXBContext.newInstance(Application.class);
        }
        catch (Exception e) {
            System.out.println(
                    "BluelinkFulfillmentRequestProcessor.enclosing_method() " + e.getMessage());
        }

    }

    public BluelinkFulfillmentRequestProcessor(RestTemplate restTemplate,
            OffersEngineDataService offersEngineDataService) {
        this.restTemplate = restTemplate;
        this.offersEngineDataService = offersEngineDataService;
    }

    public FulfillmentServiceResponse processRequest(String url,
            QuoteOfferFulfillment quoteOfferFulfillment, String authkey, String value) {
        Application application = new Application();
        String xml = "";
        try {
            Writer w = new StringWriter();
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.marshal(application, w);
            xml = w.toString();
        }
        catch (Exception e) {
            LOG.error(
                    "BluelinkFulfillmentRequestProcessor.processRequest() ==> Not able to generate Inbound XML for Bluelink PassThrough "
                            + e.getMessage());
        }

        FulfillmentServiceResponse fulfillmentServiceResponse = new FulfillmentServiceResponse();
        ResponseEntity<?> response = null;
        try {
            response = postToBlueLink(url, xml, authkey, value, String.class);
        }
        catch (Exception e) {
            LOG.error("BluelinkFulfillmentRequestProcessor.processRequest() ==> Error  "
                    + e.getMessage());
            fulfillmentServiceResponse.setSuccess(false);
        }

        if (response != null && response.getBody() != null) {
            fulfillmentServiceResponse
                    .setSuccess(response.getBody().toString().startsWith(SUCCESS_RESPONSE));
            fulfillmentServiceResponse.setServiceResponse(response.getBody().toString());
        }
        else {
            fulfillmentServiceResponse.setSuccess(false);
        }
        return fulfillmentServiceResponse;
    }

    public ResponseEntity<?> postToBlueLink(String url, String submitLoanRequestXML, String authkey,
            String value, Class<?> type) {
        ResponseEntity<?> response = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(authkey, value);
            HttpEntity<Object> request = new HttpEntity<>(submitLoanRequestXML, headers);
            response = restTemplate.exchange(url, HttpMethod.POST, request, type);
        }
        catch (RestClientException e) {
            LOG.error("Exception while posting data to BlueOps: {}, {}", e.getMessage(), e);
        }
        catch (Exception e) {
            LOG.error("Exception while posting data to BlueOps: {}, {}", e.getMessage(), e);
        }

        return response;
    }

}
