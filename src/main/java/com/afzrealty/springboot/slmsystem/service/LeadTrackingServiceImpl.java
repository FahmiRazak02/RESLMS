package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.dao.LeadTrackingRepository;
import com.afzrealty.springboot.slmsystem.entity.LeadRecord;
import com.afzrealty.springboot.slmsystem.entity.LeadTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeadTrackingServiceImpl implements LeadTrackingService{

    private LeadTrackingRepository leadTrackingRepository;

    @Autowired
    public LeadTrackingServiceImpl(LeadTrackingRepository leadTrackingRepository){
        this.leadTrackingRepository = leadTrackingRepository;
    }

    @Override
    public void addFirstStatus(LeadRecord leadRecord) {
        LocalDate initialContactDate = leadRecord.getInitialContactDate();

        LeadTracking leadTracking = new LeadTracking();
        leadTracking.setLeadRecord(leadRecord);
        leadTracking.setLeadStatus("Property Selection");
        leadTracking.setStatusDate(initialContactDate);

        leadTrackingRepository.save(leadTracking);
    }

    @Override
    public List<LeadTracking> findByUserId(Long id) {
        return leadTrackingRepository.FindByUserId(id);
    }

    @Override
    public LeadTracking findById(Long id) {
        Optional<LeadTracking> result = leadTrackingRepository.findById(id);

        LeadTracking leadTracking = null;

        if (result.isPresent()){
            leadTracking = result.get();
        }else {
            throw new RuntimeException("Did not find leadTracking id - " + id);
        }
        return leadTracking;
    }
    @Override
    public void updateLeadStatus(Long id, String status) {

        LeadTracking leadTracking = findById(id);
        leadTracking.setLeadStatus(status);
        leadTracking.setStatusDate(LocalDate.now());
        leadTrackingRepository.save(leadTracking);

    }
}
