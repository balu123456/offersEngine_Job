package com.clearlane.offersengine_job.steps;

import org.springframework.batch.item.ItemProcessor;

import com.clearlane.offersengine_job.domain.QuoteOfferFulfillment;
import com.clearlane.offersengine_job.enums.QuoteOfferFulfillmentStatus;
import com.clearlane.offersengine_job.properties.OffersEngineJobProperties;

public class QuoteOfferFullfillmentSubmitionProcessor implements ItemProcessor<QuoteOfferFulfillment, QuoteOfferFulfillment> {

    private OffersEngineJobProperties offersEngineJobProperties;
    
    public QuoteOfferFullfillmentSubmitionProcessor(
            OffersEngineJobProperties offersEngineJobProperties) {
        this.offersEngineJobProperties = offersEngineJobProperties;
    }

    /*@Bean
    private OffersEngineJobProperties properties() {
        return new OffersEngineJobProperties();
    }*/
    //private OffersEngineJobProperties offersEngineJobProperties;
    /*@Inject
    private OffersEngineJobProperties offersEngineJobProperties;*/

    @Override
    public QuoteOfferFulfillment process(QuoteOfferFulfillment item) throws Exception {
        System.out.println("Processor.process() ==> ID " + item.getId());
        System.out.println("Processor.process() ==> " + item.getLenderName());
        System.out.println(
                "QuoteOfferFullfillmentSubmitionProcessor.process() ==> source" + item.getStatus());
        item.setStatus(QuoteOfferFulfillmentStatus.PROCESSING);
        return item;
    }

}
