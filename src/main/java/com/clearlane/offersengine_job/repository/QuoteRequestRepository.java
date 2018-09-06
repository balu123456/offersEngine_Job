package com.clearlane.offersengine_job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.clearlane.offersengine_job.domain.QuoteRequest;


public interface QuoteRequestRepository extends JpaRepository<QuoteRequest, Long>, JpaSpecificationExecutor<QuoteRequest> {

}
