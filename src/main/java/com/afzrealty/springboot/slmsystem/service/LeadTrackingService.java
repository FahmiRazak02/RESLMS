package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.entity.LeadRecord;
import com.afzrealty.springboot.slmsystem.entity.LeadTracking;

import java.util.List;
import java.util.Optional;

public interface LeadTrackingService {

    void addFirstStatus(LeadRecord leadRecord);
    List<LeadTracking> findByUserId(Long id);

    LeadTracking findById(Long id);
    void updateLeadStatus(Long id, String status);
}
