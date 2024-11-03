package com.afzrealty.springboot.slmsystem.controller;

import com.afzrealty.springboot.slmsystem.entity.HouseListing;
import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.service.HouseListingService;
import com.afzrealty.springboot.slmsystem.service.LeadRecordService;
import com.afzrealty.springboot.slmsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    private HouseListingService houseListingService;
    private LeadRecordService leadRecordService;

    private UsersService usersService;

    @Autowired
    public DashboardController(HouseListingService houseListingService, LeadRecordService leadRecordService, UsersService usersService) {
        this.houseListingService = houseListingService;
        this.leadRecordService = leadRecordService;
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String getAllHouseListing(Model model, Principal principal) {
        List<HouseListing> houseListings = houseListingService.getAllHouseListings();
        User user = usersService.findUserByUserName(principal.getName());

        long leadCount = leadRecordService.countByUserId(user.getId());
        double lcr = leadRecordService.calculateLCR(user.getId());

        model.addAttribute("houseListings", houseListings);
        model.addAttribute("leadCounts", leadCount);
        model.addAttribute("leadConversionRate", lcr);

        return "dashboard";
    }
}

