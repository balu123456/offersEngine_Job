package com.clearlane.offersengine_job.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.clearlane.offersengine_job.enums.ApplicantEmployerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Quote Applicant.
 */
@Entity
@Table(name = "quote_applicant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuoteApplicant extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7470276144248521969L;

    @ManyToOne
    @JoinColumn(name = "quote_request_id")
    private QuoteRequest quoteRequest;

    @Column(name = "partner_applicant_ref_num", nullable = false)
    private String partnerApplicantRefNum;

    @Column(name = "applicant_type")
    private String applicantType;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "name_suffix")
    private String nameSuffix;

    @Column(name = "email")
    private String email;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "annual_income")
    private Double annualIncome;

    @Column(name = "annual_additional_income")
    private Double annualAdditionalIncome;

    @Column(name = "annual_housing_expense")
    private Double annualHousingExpense;

    @Column(name = "housing_status")
    private String housingStatus;

    @Column(name = "quote_credit_info_id")
    private Long quoteCreditInfoId;

    @OneToMany(mappedBy = "quoteApplicant", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApplicantAddress> applicantAddresses = new HashSet<>();

    @OneToMany(mappedBy = "quoteApplicant", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApplicantEmployer> applicantEmployers = new HashSet<>();

    public QuoteRequest getQuoteRequest() {
        return quoteRequest;
    }

    public void setQuoteRequest(QuoteRequest quoteRequest) {
        this.quoteRequest = quoteRequest;
    }

    public String getPartnerApplicantRefNum() {
        return partnerApplicantRefNum;
    }

    public void setPartnerApplicantRefNum(String partnerApplicantRefNum) {
        this.partnerApplicantRefNum = partnerApplicantRefNum;
    }

    public String getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(String applicantType) {
        this.applicantType = applicantType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Double getAnnualAdditionalIncome() {
        return annualAdditionalIncome;
    }

    public void setAnnualAdditionalIncome(Double annualAdditionalIncome) {
        this.annualAdditionalIncome = annualAdditionalIncome;
    }

    public Double getAnnualHousingExpense() {
        return annualHousingExpense;
    }

    public void setAnnualHousingExpense(Double annualHousingExpense) {
        this.annualHousingExpense = annualHousingExpense;
    }

    public String getHousingStatus() {
        return housingStatus;
    }

    public void setHousingStatus(String housingStatus) {
        this.housingStatus = housingStatus;
    }

    public Long getQuoteCreditInfoId() {
        return quoteCreditInfoId;
    }

    public void setQuoteCreditInfoId(Long quoteCreditInfoId) {
        this.quoteCreditInfoId = quoteCreditInfoId;
    }

    public Set<ApplicantAddress> getApplicantAddresses() {
        return applicantAddresses;
    }

    public void setApplicantAddresses(Set<ApplicantAddress> applicantAddresses) {
        this.applicantAddresses = applicantAddresses;
    }

    public Set<ApplicantEmployer> getApplicantEmployers() {
        return applicantEmployers;
    }

    public void setApplicantEmployers(Set<ApplicantEmployer> applicantEmployers) {
        this.applicantEmployers = applicantEmployers;
    }

    public ApplicantAddress getCurrentAddress() {
        for (ApplicantAddress applicantAddress : applicantAddresses) {
            if (ApplicantAddressType.CURRENT.equals(applicantAddress.getAddressType())) {
                return applicantAddress;
            }
        }
        return null;
    }

    public ApplicantEmployer getCurrentEmployer() {
        for (ApplicantEmployer applicantEmployer : applicantEmployers) {
            if (ApplicantEmployerType.CURRENT.equals(applicantEmployer.getEmployerType())) {
                return applicantEmployer;
            }
        }
        return null;
    }

    public void addAddress(ApplicantAddress applicantAddress) {
        applicantAddress.setQuoteApplicant(this);
        Set<ApplicantAddress> addresses = this.getApplicantAddresses();
        if (addresses == null) {
            addresses = new HashSet<>();
            this.setApplicantAddresses(addresses);
        }
        if (!addresses.contains(applicantAddress)) {
            addresses.add(applicantAddress);
        }
    }

    public void addEmployer(ApplicantEmployer applicantEmployer) {
        applicantEmployer.setQuoteApplicant(this);
        Set<ApplicantEmployer> employers = this.getApplicantEmployers();
        if (employers == null) {
            employers = new HashSet<>();
            this.setApplicantEmployers(employers);
        }
        if (!employers.contains(applicantEmployer)) {
            employers.add(applicantEmployer);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuoteApplicant quoteApplicant = (QuoteApplicant) o;
        if (quoteApplicant.getId() == null) {
            return false;
        }

        return Objects.equals(getId(), quoteApplicant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuoteApplicant [quoteRequest=" + quoteRequest + ", partnerApplicantRefNum="
                + partnerApplicantRefNum + ", applicantType=" + applicantType + ", firstName="
                + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", nameSuffix=" + nameSuffix + ", email=" + email + ", ssn=" + ssn
                + ", dateOfBirth=" + dateOfBirth + ", mobilePhone=" + mobilePhone + ", homePhone="
                + homePhone + ", workPhone=" + workPhone + ", annualIncome=" + annualIncome
                + ", annualAdditionalIncome=" + annualAdditionalIncome + ", annualHousingExpense="
                + annualHousingExpense + ", housingStatus=" + housingStatus + ", quoteCreditInfoId="
                + quoteCreditInfoId + "]";
    }

}
