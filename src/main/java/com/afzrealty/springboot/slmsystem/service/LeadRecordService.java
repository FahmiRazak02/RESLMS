    package com.afzrealty.springboot.slmsystem.service;

    import com.afzrealty.springboot.slmsystem.entity.LeadRecord;

    import java.util.List;
    import java.util.Optional;

    public interface LeadRecordService {

        List<LeadRecord> findByUserId(Long userId);

        LeadRecord save(LeadRecord leadRecord);

        LeadRecord update(LeadRecord leadRecord);

        void deleteByiD(Long id);

        LeadRecord findById2(long id);

        Optional<LeadRecord> findById(Long id);

        long countByUserId(Long userId);

        double calculateLCR(Long userId);

    }
