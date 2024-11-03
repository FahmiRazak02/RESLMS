package com.afzrealty.springboot.slmsystem.dao;

import com.afzrealty.springboot.slmsystem.entity.HouseListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseListingRepository extends JpaRepository<HouseListing, Long> {

    List<HouseListing> findByUserId(Long userId);
}
