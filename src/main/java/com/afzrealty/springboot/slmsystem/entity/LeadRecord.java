package com.afzrealty.springboot.slmsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lead_records")
public class LeadRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "loan_cap")
    private Double loanCap;

    @Column(name = "interested_property")
    private String interestedProperty;

    @Column(name = "initial_contact_date")
    private LocalDate initialContactDate;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading for user
    @JoinColumn(name = "user_id")
    @JsonBackReference // Breaks the loop for JSON serialization
    private User user;

    @OneToMany(mappedBy = "leadRecord", cascade = CascadeType.ALL)
    @JsonManagedReference // Manages the bidirectional relationship
    private List<FollowUp> followUps;

    public LeadRecord() {
    }

    public LeadRecord(String fullName, String phoneNumber, Double loanCap, String interestedProperty, LocalDate initialContactDate) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.loanCap = loanCap;
        this.interestedProperty = interestedProperty;
        this.initialContactDate = initialContactDate;
    }

    @PrePersist
    protected void onCreate() {
        initialContactDate = LocalDate.now();
    }

    // Getters and setters
    // Ensure getters and setters are implemented for all fields

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<FollowUp> getFollowUps() {
        return followUps;
    }

    public void setFollowUps(List<FollowUp> followUps) {
        this.followUps = followUps;
    }
}
