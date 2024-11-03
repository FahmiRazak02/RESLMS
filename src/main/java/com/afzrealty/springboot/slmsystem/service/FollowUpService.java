package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.entity.FollowUp;
import com.afzrealty.springboot.slmsystem.entity.LeadRecord;

import java.util.List;
import java.util.Optional;

public interface FollowUpService {
    void updateFirstStage(LeadRecord leadRecord);

    void updateSecondStage(Long id);

    void updateThirdStage(Long id);

    List<FollowUp> findByIdAndStage(Long userId, String stage);

    void deleteById(Long id);

    Optional<FollowUp> findById(Long id);
}
