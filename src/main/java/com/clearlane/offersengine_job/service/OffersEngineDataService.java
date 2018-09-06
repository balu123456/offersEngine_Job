package com.clearlane.offersengine_job.service;

import com.clearlane.offersengine_job.domain.QuoteRequest;
import com.clearlane.offersengine_job.repository.QuoteRequestRepository;


public class OffersEngineDataService {

    private QuoteRequestRepository quoteRequestRepository;

    public OffersEngineDataService(QuoteRequestRepository quoteRequestRepository) {
        this.quoteRequestRepository = quoteRequestRepository;
    }

    public QuoteRequest getQuoteRequest(Long quoteRequestId) {
        return quoteRequestRepository.findOne(quoteRequestId);
    }

}
