package com.clearlane.offersengine_job.dataservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.clearlane.offersengine_job.domain.QuoteOfferFulfillment;
import com.clearlane.offersengine_job.enums.QuoteOfferFulfillmentStatus;
import com.clearlane.offersengine_job.repository.QuoteOfferFulfillmentRepository;

@Component
@Transactional
public class QuoteOfferFulfillmentService {

    @Autowired
    private QuoteOfferFulfillmentRepository fulfillmentRepository;

    public void loadFullfillment() {

        System.out.println("QuoteOfferFulfillmentService.loadFullfillment() ==> ");
        /*List<QuoteOfferFulfillment> fulfillments = fulfillmentRepository
                .findByStatus(QuoteOfferFulfillmentStatus.FULFILLMENT_READY);*/
        List<QuoteOfferFulfillment> fulfillments = fulfillmentRepository
                .findAllByStatus(QuoteOfferFulfillmentStatus.FULFILLMENT_READY);
        System.out.println(
                "QuoteOfferFulfillmentService.loadFullfillment() ==> " + fulfillments.size());

    }

}
