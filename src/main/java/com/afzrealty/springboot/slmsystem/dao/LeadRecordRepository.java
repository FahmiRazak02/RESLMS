package com.afzrealty.springboot.slmsystem.dao;

import com.afzrealty.springboot.slmsystem.entity.LeadRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRecordRepository extends JpaRepository<LeadRecord, Long> {
    List<LeadRecord> findByUserId(Long userId);
    long countByUserId(Long userId);

}
