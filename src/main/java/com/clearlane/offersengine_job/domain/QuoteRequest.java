package com.clearlane.offersengine_job.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.Specification;

import com.clearlane.offersengine_job.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Quote Request.
 */
@Entity
@Table(name = "quote_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuoteRequest extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2278503799999952772L;

    @OneToOne
    @JoinColumn(name = "quote_origination_id")
    private QuoteOrigination quoteorigination;

    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "partner_client_id")
    private Long partnerClientId;

    @Column(name = "partner_tracking_num")
    private String partnerTrackingNum;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "quote_type")
    private String quoteType;

    @Column(name = "status")
    private String status;

    @Column(name = "current_state")
    private String currentState;

    @Column(name = "auth_fail_count")
    private Integer authFailCount = 0;

    @OneToMany(mappedBy = "quoteRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuoteApplicant> quoteApplicants = new HashSet<>();

    @OneToMany(mappedBy = "quoteRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuoteOffer> quoteOffers = new HashSet<>();

    @OneToMany(mappedBy = "quoteRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuoteVehicle> quoteVehicles = new HashSet<>();

    @OneToOne(mappedBy = "quoteRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private QuoteRequestMiscData quoteRequestMiscData;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public QuoteOrigination getQuoteorigination() {
        return quoteorigination;
    }

    public void setQuoteorigination(QuoteOrigination quoteorigination) {
        this.quoteorigination = quoteorigination;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getPartnerClientId() {
        return partnerClientId;
    }

    public void setPartnerClientId(Long partnerClientId) {
        this.partnerClientId = partnerClientId;
    }

    public String getPartnerTrackingNum() {
        return partnerTrackingNum;
    }

    public void setPartnerTrackingNum(String partnerTrackingNum) {
        this.partnerTrackingNum = partnerTrackingNum;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public Set<QuoteApplicant> getQuoteApplicants() {
        return quoteApplicants;
    }

    public void setQuoteApplicants(Set<QuoteApplicant> quoteApplicants) {
        this.quoteApplicants = quoteApplicants;
    }

    public Set<QuoteOffer> getQuoteOffers() {
        return quoteOffers;
    }

    public void setQuoteOffers(Set<QuoteOffer> quoteOffers) {
        this.quoteOffers = quoteOffers;
    }

    public Set<QuoteVehicle> getQuoteVehicles() {
        return this.quoteVehicles;
    }

    public void setQuoteVehicles(Set<QuoteVehicle> quoteVehicles) {
        this.quoteVehicles = quoteVehicles;
    }

    public QuoteRequestMiscData getQuoteRequestMiscData() {
        return quoteRequestMiscData;
    }

    public void setQuoteRequestMiscData(QuoteRequestMiscData quoteRequestMiscData) {
        this.quoteRequestMiscData = quoteRequestMiscData;
    }

    public Integer getAuthFailCount() {
        return authFailCount;
    }

    public void setAuthFailCount(Integer authFailCount) {
        this.authFailCount = authFailCount;
    }

    public QuoteApplicant getPrimaryApplicant() {
        for (QuoteApplicant quoteApplicant : getQuoteApplicants()) {
            if ("Primary".equalsIgnoreCase(quoteApplicant.getApplicantType())) {
                return quoteApplicant;
            }
        }
        return null;
    }

    public QuoteApplicant getCoApplicant() {
        for (QuoteApplicant quoteApplicant : getQuoteApplicants()) {
            if ("CoApp".equalsIgnoreCase(quoteApplicant.getApplicantType())) {
                return quoteApplicant;
            }
        }
        return null;
    }

    public void addQuoteApplicant(QuoteApplicant quoteApplicant) {
        quoteApplicant.setQuoteRequest(this);
        Set<QuoteApplicant> applicants = this.getQuoteApplicants();
        if (applicants == null) {
            applicants = new HashSet<>();
            this.setQuoteApplicants(applicants);
        }

        if (!applicants.contains(quoteApplicant)) {
            applicants.add(quoteApplicant);
        }
    }

    public void addQuoteOffer(QuoteOffer quoteOffer) {

        quoteOffer.setQuoteRequest(this);

        if (getQuoteOffers() != null && !getQuoteOffers().contains(quoteOffer)) {
            getQuoteOffers().add(quoteOffer);
        }
        else if (getQuoteOffers() == null) {
            Set<QuoteOffer> quoteofferslist = new HashSet<>();
            quoteofferslist.add(quoteOffer);
            setQuoteOffers(quoteofferslist);
        }
    }

    public void addQuoteVehicle(QuoteVehicle quoteVehicle) {

        quoteVehicle.setQuoteRequest(this);

        if (getQuoteVehicles() != null && !getQuoteVehicles().contains(quoteVehicle)) {
            getQuoteVehicles().add(quoteVehicle);
        }
        else if (getQuoteVehicles() == null) {
            Set<QuoteVehicle> quotevehiclelist = new HashSet<>();
            quotevehiclelist.add(quoteVehicle);
            setQuoteVehicles(quotevehiclelist);
        }
    }

    public QuoteVehicle getLoanVehicle() {
        if (CollectionUtils.isNotEmpty(quoteVehicles)) {
            List<QuoteVehicle> vehiclesList = new ArrayList<>(quoteVehicles);
            Collections.sort(vehiclesList,
                    (o1, o2) -> o1.getId().intValue() - o2.getId().intValue());

            return vehiclesList.get(vehiclesList.size() - 1);
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuoteRequest quoteRequest = (QuoteRequest) o;
        if (quoteRequest.getId() == null) {
            return false;
        }

        return Objects.equals(getId(), quoteRequest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public static Specification<QuoteRequest> searchByPartnerId(Long partnerId) {
        return new Specification<QuoteRequest>() {
            @Override
            public Predicate toPredicate(Root<QuoteRequest> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                return cb.equal(root.get("partnerId"), partnerId);
            }
        };
    }

    public static Specification<QuoteRequest> searchByVin(String vin) {
        return new Specification<QuoteRequest>() {
            @Override
            public Predicate toPredicate(Root<QuoteRequest> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                Join<QuoteRequest, QuoteVehicle> vehicleRelation = root.join("quoteVehicles");
                return cb.equal(vehicleRelation.get("vehicleVin"), vin);
            }
        };
    }

    public static Specification<QuoteRequest> searchByOriginalApplicationId(
            String originalApplicationId) {
        return new Specification<QuoteRequest>() {
            @Override
            public Predicate toPredicate(Root<QuoteRequest> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                Join<QuoteRequest, QuoteRequestMiscData> quoteRequestMiscDataRelation = root
                        .join("quoteRequestMiscData");
                return cb.equal(quoteRequestMiscDataRelation.get("destinationAppNumber"),
                        originalApplicationId);
            }
        };
    }

    public static Specification<QuoteRequest> searchByVehicleId(Long vehicleId) {
        return new Specification<QuoteRequest>() {
            @Override
            public Predicate toPredicate(Root<QuoteRequest> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                Join<QuoteRequest, QuoteVehicle> vehicleRelation = root.join("quoteVehicles");
                return cb.equal(vehicleRelation.get("id"), vehicleId);
            }
        };
    }

    public static Specification<QuoteRequest> search(String firstname, String lastname, String ssn,
            String phone, String email, Date dob) {
        return (Root<QuoteRequest> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            Join<QuoteRequest, QuoteApplicant> applicantRelation = root.join("quoteApplicants");
            if (StringUtils.isNotEmpty(firstname)) {
                predicates.add(cb.like(applicantRelation.get("firstName"), firstname + "%"));
            }
            if (StringUtils.isNotEmpty(lastname)) {
                predicates.add(cb.like(applicantRelation.get("lastName"), lastname + "%"));
            }
            if (StringUtils.isNotEmpty(ssn)) {
                List<String> ssnList = new ArrayList<>();
                ssnList.add(ssn);
                ssnList.add(ssn.replace("-", ""));
                predicates.add(applicantRelation.get("ssn").in(ssnList));
            }
            if (StringUtils.isNotEmpty(phone)) {
                predicates.add(cb.equal(applicantRelation.get("mobilePhone"), phone));
            }
            if (StringUtils.isNotEmpty(email)) {
                predicates.add(cb.equal(applicantRelation.get("email"), email));
            }
            if (dob != null) {
                predicates.add(cb.equal(applicantRelation.<Date> get("dateOfBirth"),
                        DateUtils.dateStart(dob)));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
