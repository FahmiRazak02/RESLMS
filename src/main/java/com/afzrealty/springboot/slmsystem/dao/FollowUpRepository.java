package com.afzrealty.springboot.slmsystem.dao;
import com.afzrealty.springboot.slmsystem.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {
    // You can add custom query methods here if needed

    @Query("SELECT f FROM FollowUp f JOIN f.leadRecord l WHERE l.user.id = :userId AND f.stage = :stage")
    List<FollowUp> findByUserIdAndStage(Long userId, String stage);
}
