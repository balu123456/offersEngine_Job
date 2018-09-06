package com.clearlane.offersengine_job.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.clearlane.offersengine_job.domain.QuoteOfferFulfillment;
import com.clearlane.offersengine_job.enums.QuoteOfferFulfillmentStatus;

@Component
@Repository
public interface QuoteOfferFulfillmentRepository
        extends JpaRepository<QuoteOfferFulfillment, Long> {

    List<QuoteOfferFulfillment> findAllByStatus(QuoteOfferFulfillmentStatus status);

    Page<QuoteOfferFulfillment> findTop10ByStatus(QuoteOfferFulfillmentStatus status,
            Pageable pageable);
}
