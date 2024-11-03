package com.afzrealty.springboot.slmsystem.controller;

import com.afzrealty.springboot.slmsystem.entity.FollowUp;
import com.afzrealty.springboot.slmsystem.entity.LeadTracking;
import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.service.LeadRecordService;
import com.afzrealty.springboot.slmsystem.service.LeadTrackingService;
import com.afzrealty.springboot.slmsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/leadTracking")
public class LeadTrackingController {

    private LeadTrackingService leadTrackingService;
    private UsersService usersService;

    /*private LeadRecordDTO leadRecordDTO;*/

    @Autowired
    public LeadTrackingController(LeadTrackingService leadTrackingService, UsersService usersService){

        this.leadTrackingService = leadTrackingService;
        this.usersService = usersService;
    }
    @GetMapping("statusList")
    public String statusList(Model model, Principal principal) {
        User user = usersService.findUserByUserName(principal.getName());
        // Get the LeadTracking records by user ID
        List<LeadTracking> leadTrackings = leadTrackingService.findByUserId(user.getId());

        // Custom sorting logic
        Map<String, Integer> statusOrder = Map.of(
                "Property Viewing", 1,
                "Property Selection", 2,
                "Loan Approval", 3,
                "Sales", 4
        );

        leadTrackings.sort(Comparator.comparingInt(
                leadTracking -> statusOrder.getOrDefault(leadTracking.getLeadStatus(), Integer.MAX_VALUE)
        ));

        // Add the LeadTracking records to the model
        model.addAttribute("leadTrackings", leadTrackings);
        // Return the view name
        return "leadTracking/status-list";
    }

    @PostMapping("/{id}/edit")
    @ResponseBody
    public ResponseEntity<String> editLeadStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        try {
            leadTrackingService.updateLeadStatus(id, status);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update status");
        }
    }

}
