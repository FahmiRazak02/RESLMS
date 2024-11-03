package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.entity.HouseListing;

import java.util.List;

public interface HouseListingService {

    List<HouseListing> findHouseByUserId(Long id);

    HouseListing save(HouseListing houseListing);

    HouseListing findById(long id);

    void deleteById(long id);

    List<HouseListing> getAllHouseListings();
}
