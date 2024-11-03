package com.afzrealty.springboot.slmsystem.dto;

import java.time.LocalDate;

public class LeadRecordDTO {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private Double loanCap;
    private String interestedProperty;
    private LocalDate initialContactDate;
    private Long userId;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getLoanCap() {
        return loanCap;
    }

    public void setLoanCap(Double loanCap) {
        this.loanCap = loanCap;
    }

    public String getInterestedProperty() {
        return interestedProperty;
    }

    public void setInterestedProperty(String interestedProperty) {
        this.interestedProperty = interestedProperty;
    }

    public LocalDate getInitialContactDate() {
        return initialContactDate;
    }

    public void setInitialContactDate(LocalDate initialContactDate) {
        this.initialContactDate = initialContactDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
