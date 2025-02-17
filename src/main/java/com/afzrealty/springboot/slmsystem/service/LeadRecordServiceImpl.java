package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.dao.LeadRecordRepository;
import com.afzrealty.springboot.slmsystem.dao.LeadTrackingRepository;
import com.afzrealty.springboot.slmsystem.entity.LeadRecord;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class LeadRecordServiceImpl implements LeadRecordService{

    private LeadRecordRepository leadRecordRepository;
    private FollowUpService followUpService;
    private LeadTrackingService leadTrackingService;
    private LeadTrackingRepository leadTrackingRepository;

    public LeadRecordServiceImpl(LeadRecordRepository leadRecordRepository, FollowUpService followUpService, LeadTrackingService leadTrackingService, LeadTrackingRepository leadTrackingRepository){
        this.leadRecordRepository = leadRecordRepository;
        this.followUpService = followUpService;
        this.leadTrackingService = leadTrackingService;
        this.leadTrackingRepository = leadTrackingRepository;
    }

    @Override
    public List<LeadRecord> findByUserId(Long userId) {
        return leadRecordRepository.findByUserId(userId);
    }

    @Override
    public Optional<LeadRecord> findById(Long id) {
        return leadRecordRepository.findById(id);
    }

    @Override
    public long countByUserId(Long userId) {
        return leadRecordRepository.countByUserId(userId);
    }

    @Override
    public double calculateLCR(Long userId) {
        double totalLeads = leadRecordRepository.countByUserId(userId);
        double salesLeads = leadTrackingRepository.countByUserIdAndLeadStatusSales(userId);

        if (totalLeads == 0) {
            return 0;
        }

        // Calculate the percentage
        double lcr = ((double) salesLeads / totalLeads) * 100;

        // Round to 2 decimal places
        BigDecimal roundedLcr = new BigDecimal(lcr).setScale(2, RoundingMode.HALF_UP);

        return roundedLcr.doubleValue();
    }

    @Override
    public LeadRecord findById2(long id) {
        Optional<LeadRecord> result = leadRecordRepository.findById(id);

        LeadRecord leadRecord = null;

        if (result.isPresent()){
            leadRecord = result.get();
        }else {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return leadRecord;
    }

    @Override
    public LeadRecord save(LeadRecord leadRecord) {
        leadRecord = leadRecordRepository.save(leadRecord); // Save lead record and update object if necessary
        followUpService.updateFirstStage(leadRecord); // Schedule follow-ups
        leadTrackingService.addFirstStatus(leadRecord);
        return leadRecord; // Return the saved lead record
    }

    @Override
    public LeadRecord update(LeadRecord leadRecord) {
        leadRecord = leadRecordRepository.save(leadRecord); // Save lead record and update object if necessary
        return leadRecord; // Return the saved lead record
    }

    @Override
    public void deleteByiD(Long id) {
        leadRecordRepository.deleteById(id);
    }



}
