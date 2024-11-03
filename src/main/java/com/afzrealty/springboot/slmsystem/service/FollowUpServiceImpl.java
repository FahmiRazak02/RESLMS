package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.dao.FollowUpRepository;
import com.afzrealty.springboot.slmsystem.entity.FollowUp;
import com.afzrealty.springboot.slmsystem.entity.LeadRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FollowUpServiceImpl implements FollowUpService {

    @Autowired
    private FollowUpRepository followUpRepository;

    @Override
    public void updateFirstStage(LeadRecord leadRecord) {
        LocalDate initialContactDate = leadRecord.getInitialContactDate();
        LocalDate followUpDate = initialContactDate.plusDays(2);

        FollowUp followUp = new FollowUp();
        followUp.setLeadRecord(leadRecord);
        followUp.setDate(followUpDate);
        followUp.setStage("First");

        followUpRepository.save(followUp);
    }

    @Override
    public Optional<FollowUp> findById(Long id) {
        return followUpRepository.findById(id);
    }

    @Override
    public void updateSecondStage(Long id) {
        Optional<FollowUp> followUp = followUpRepository.findById(id);
        if (followUp.isPresent()){
            FollowUp secondFollowUp =  followUp.get();
            LocalDate secondDate =  secondFollowUp.getDate().plusWeeks(2);
            secondFollowUp.setDate(secondDate);
            secondFollowUp.setStage("Second");
            followUpRepository.save(secondFollowUp);
        }
    }

    @Override
    public void updateThirdStage(Long id) {
        Optional<FollowUp> followUp = followUpRepository.findById(id);
        if (followUp.isPresent()){
            FollowUp thirdFollowUp =  followUp.get();
            LocalDate thirdDate =  thirdFollowUp.getDate().plusMonths(2);
            thirdFollowUp.setDate(thirdDate);
            thirdFollowUp.setStage("Third");
            followUpRepository.save(thirdFollowUp);
        }
    }

    @Override
    public List<FollowUp> findByIdAndStage(Long userId, String stage) {
        return followUpRepository.findByUserIdAndStage(userId, stage);
    }

    @Override
    public void deleteById(Long id) {
        followUpRepository.deleteById(id);
    }
}
