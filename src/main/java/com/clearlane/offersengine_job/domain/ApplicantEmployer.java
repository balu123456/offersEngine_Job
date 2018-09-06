/**
 * 
 */
package com.clearlane.offersengine_job.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.clearlane.offersengine_job.enums.ApplicantEmployerType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sasikumar.velusamy
 *
 */
@Entity
@Table(name = "applicant_employer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicantEmployer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8165377285478867198L;

    @ManyToOne
    @JoinColumn(name = "quote_applicant_id")
    private QuoteApplicant quoteApplicant;

    @Enumerated(EnumType.STRING)
    @Column(name = "applicant_employer_type")
    private ApplicantEmployerType applicantEmployerType;

    @Column(name = "employer_type")
    private String employerType;

    @Column(name = "employer_name", length = 255)
    private String employerName;

    @Column(name = "job_title", length = 255)
    private String jobTitle;

    @Column(name = "employer_phone", length = 12)
    private String employerPhone;

    @Column(name = "employer_email", length = 255)
    private String employerEmail;

    @Column(name = "address_line1", length = 255)
    private String addressLine1;

    @Column(name = "address_line2", length = 255)
    private String addressLine2;

    @Column(name = "address_city", length = 50)
    private String addressCity;

    @Column(name = "address_state", length = 50)
    private String addressState;

    @Column(name = "address_zip", length = 10)
    private String addressZip;

    @Column(name = "start_date_at_employer")
    private String startDateAtEmployer;

    @Column(name = "end_date_at_employer")
    private String endDateAtEmployer;

    @Column(name = "years_at_employment")
    private Integer yearsAtEmployment;

    @Column(name = "months_at_employment")
    private Integer monthsAtEmployment;

    public QuoteApplicant getQuoteApplicant() {
        return quoteApplicant;
    }

    public void setQuoteApplicant(QuoteApplicant quoteApplicant) {
        this.quoteApplicant = quoteApplicant;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getEmployerType() {
        return employerType;
    }

    public void setEmployerType(String employerType) {
        this.employerType = employerType;
    }

    public ApplicantEmployerType getApplicantEmployerType() {
        return applicantEmployerType;
    }

    public void setApplicantEmployerType(ApplicantEmployerType applicantEmployerType) {
        this.applicantEmployerType = applicantEmployerType;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmployerPhone() {
        return employerPhone;
    }

    public void setEmployerPhone(String employerPhone) {
        this.employerPhone = employerPhone;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getStartDateAtEmployer() {
        return startDateAtEmployer;
    }

    public void setStartDateAtEmployer(String startDateAtEmployer) {
        this.startDateAtEmployer = startDateAtEmployer;
    }

    public String getEndDateAtEmployer() {
        return endDateAtEmployer;
    }

    public void setEndDateAtEmployer(String endDateAtEmployer) {
        this.endDateAtEmployer = endDateAtEmployer;
    }

    public Integer getYearsAtEmployment() {
        return yearsAtEmployment;
    }

    public void setYearsAtEmployment(Integer yearsAtEmployment) {
        this.yearsAtEmployment = yearsAtEmployment;
    }

    public Integer getMonthsAtEmployment() {
        return monthsAtEmployment;
    }

    public void setMonthsAtEmployment(Integer monthsAtEmployment) {
        this.monthsAtEmployment = monthsAtEmployment;
    }

    @Override
    public String toString() {
        return "ApplicantEmployer [quoteApplicant=" + quoteApplicant + ", applicantEmployerType="
                + applicantEmployerType + ", employerType=" + employerType + ", employerName="
                + employerName + ", jobTitle=" + jobTitle + ", employerPhone=" + employerPhone
                + ", employerEmail=" + employerEmail + ", addressLine1=" + addressLine1
                + ", addressLine2=" + addressLine2 + ", addressCity=" + addressCity
                + ", addressState=" + addressState + ", addressZip=" + addressZip
                + ", startDateAtEmployer=" + startDateAtEmployer + ", endDateAtEmployer="
                + endDateAtEmployer + ", yearsAtEmployment=" + yearsAtEmployment
                + ", monthsAtEmployment=" + monthsAtEmployment + "]";
    }

}
