package com.clearlane.offersengine_job.steps;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;

import com.clearlane.offersengine_job.constants.OffersEngineConstants;
import com.clearlane.offersengine_job.domain.QuoteOfferFulfillment;
import com.clearlane.offersengine_job.dto.FulfillmentServiceResponse;
import com.clearlane.offersengine_job.enums.QuoteOfferFulfillmentStatus;
import com.clearlane.offersengine_job.properties.OffersEngineJobProperties;
import com.clearlane.offersengine_job.repository.QuoteOfferFulfillmentRepository;
import com.clearlane.offersengine_job.requestprocessor.BluelinkFulfillmentRequestProcessor;
import com.clearlane.offersengine_job.web.util.StringUtils;

public class Writer implements ItemWriter<QuoteOfferFulfillment> {


    public QuoteOfferFulfillmentRepository fulfillmentRepository;
    private EntityManagerFactory entityManagerFactory;
    private OffersEngineJobProperties offersEngineJobProperties;
    private BluelinkFulfillmentRequestProcessor bluelinkFulfillmentRequestProcessor;

    public Writer(QuoteOfferFulfillmentRepository fulfillmentRepository,
            EntityManagerFactory entityManagerFactory,
            OffersEngineJobProperties offersEngineJobProperties,
            BluelinkFulfillmentRequestProcessor bluelinkFulfillmentRequestProcessor) {
        this.fulfillmentRepository = fulfillmentRepository;
        this.entityManagerFactory = entityManagerFactory;
        this.offersEngineJobProperties = offersEngineJobProperties;
        this.bluelinkFulfillmentRequestProcessor = bluelinkFulfillmentRequestProcessor;
    }

    @Override
    public void write(List<? extends QuoteOfferFulfillment> items)
            throws Exception {
        JpaItemWriter<QuoteOfferFulfillment> itemWriter = new JpaItemWriter<QuoteOfferFulfillment>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);
        itemWriter.write(items);
        itemWriter.afterPropertiesSet();

        for (QuoteOfferFulfillment fulfillment : items) {
            System.out.println("Writer.write() ==> id" + fulfillment.getLenderName() + "service"
                    + fulfillment.getService().getServiceName());

            FulfillmentServiceResponse fulfillmentServiceResponse = new QuoteFullfillmentService(
                    offersEngineJobProperties,
                    bluelinkFulfillmentRequestProcessor)
                    .processService(fulfillment);

            if (fulfillmentServiceResponse.getSuccess()) {
                if (OffersEngineConstants.DEALER_NOT_FOUND
                        .equals(fulfillmentServiceResponse.getServiceResponse())) {
                    fulfillment.setLastUpdatedDate(new Date());
                    fulfillment
                            .setStatus(QuoteOfferFulfillmentStatus.NOT_SUBMITTED_NO_DEALER_FOUND);
                    fulfillment.setRetryCount(fulfillment.getRetryCount() + 1);
                }
                else {
                    fulfillment.setLastUpdatedDate(new Date());
                    fulfillment.setStatus(QuoteOfferFulfillmentStatus.COMPLETED);

                    if (StringUtils
                            .isNotNullOrEmpty(fulfillmentServiceResponse.getRequestId() + "")) {
                        fulfillment.setServiceReferenceNum(
                                fulfillmentServiceResponse.getRequestId() + "");
                    }

                }

            }
            else {
                fulfillment.setLastUpdatedDate(new Date());
                fulfillment.setStatus(QuoteOfferFulfillmentStatus.FAILED);
                fulfillment.setRetryCount(fulfillment.getRetryCount() + 1);
            }


        }
        
        itemWriter.write(items);
        itemWriter.afterPropertiesSet();

    }

}
