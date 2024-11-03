package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.dao.HouseListingRepository;
import com.afzrealty.springboot.slmsystem.entity.HouseListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseListingServiceImpl implements HouseListingService{

    private HouseListingRepository houseListingRepository;

    @Autowired
    public HouseListingServiceImpl(HouseListingRepository houseListingRepository){
        this.houseListingRepository = houseListingRepository;
    }

    @Override
    public List<HouseListing> findHouseByUserId(Long id) {
        return houseListingRepository.findByUserId(id);
    }

    @Override
    public HouseListing save(HouseListing houseListing) {
        return houseListingRepository.save(houseListing);
    }

    @Override
    public HouseListing findById(long id) {
        Optional<HouseListing> result = houseListingRepository.findById(id);

        HouseListing houseListing = null;

        if (result.isPresent()) {
            houseListing = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + id);
        }

        return houseListing;
    }

    @Override
    public void deleteById(long id) {
        houseListingRepository.deleteById(id);
    }


    @Override
    public List<HouseListing> getAllHouseListings() {
        return houseListingRepository.findAll();
    }
}
