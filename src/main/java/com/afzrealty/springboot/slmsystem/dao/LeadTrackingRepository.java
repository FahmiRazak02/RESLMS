package com.afzrealty.springboot.slmsystem.dao;

import com.afzrealty.springboot.slmsystem.entity.LeadTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadTrackingRepository extends JpaRepository<LeadTracking, Long> {
    @Query("SELECT f FROM LeadTracking f JOIN f.leadRecord l WHERE l.user.id = :userId")
    List<LeadTracking> FindByUserId(Long userId);

    @Query("SELECT COUNT(f) FROM LeadTracking f JOIN f.leadRecord l WHERE l.user.id = :userId AND f.leadStatus = 'Sales'")
    long countByUserIdAndLeadStatusSales(Long userId);
}
