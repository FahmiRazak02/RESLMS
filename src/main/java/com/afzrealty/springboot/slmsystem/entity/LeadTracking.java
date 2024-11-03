package com.afzrealty.springboot.slmsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lead_status")
public class LeadTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lead_status")
    private String leadStatus;

    @Column(name = "status_date")
    private LocalDate statusDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_record_id", nullable = false)
    private LeadRecord leadRecord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public LeadRecord getLeadRecord() {
        return leadRecord;
    }

    public void setLeadRecord(LeadRecord leadRecord) {
        this.leadRecord = leadRecord;
    }

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }
}
