/**
 * 
 */
package com.clearlane.offersengine_job.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = true, updatable = false)
    private Long id;

    @Column(name = "created_dt", insertable = true, updatable = false)
    private Date createdDate;

    @Column(name = "last_updated_dt", insertable = false, updatable = true)
    private Date lastUpdatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @PrePersist
    void onCreate() {
        this.setCreatedDate(new Date());
        this.setLastUpdatedDate(new Date());
    }

    @PreUpdate
    void onUdate() {
        this.setLastUpdatedDate(new Date());
    }

}
