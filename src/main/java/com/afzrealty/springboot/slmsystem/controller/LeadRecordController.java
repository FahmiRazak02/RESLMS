package com.afzrealty.springboot.slmsystem.controller;

import com.afzrealty.springboot.slmsystem.entity.LeadRecord;
import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.service.LeadRecordService;
import com.afzrealty.springboot.slmsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
    @RequestMapping("/leadRecords")
public class LeadRecordController {

    private LeadRecordService leadRecordService;
    private UsersService usersService;

    @Autowired
    public LeadRecordController(LeadRecordService leadRecordService, UsersService usersService) {
        this.leadRecordService = leadRecordService;
        this.usersService = usersService;
    }

    @GetMapping("/showLeadRecordForm")
    public String showLeadRecordForm(Model theModel) {
        LeadRecord leadRecord = new LeadRecord();
        theModel.addAttribute("leadRecord", leadRecord);
        return "leadRecords/lead-record-form";
    }

    @PostMapping("/saveLeadRecord")
    public String saveLeadRecord(@ModelAttribute("leadRecord") LeadRecord leadRecord,Model model, RedirectAttributes redirectAttributes) {
        try {
            // Get the logged-in user
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            User user = usersService.findUserByUserName(username);

            // Set the user and save the lead record
            leadRecord.setUser(user);
            leadRecordService.save(leadRecord);

            // Add flash attribute for success message
            redirectAttributes.addFlashAttribute("successMessage", "Lead record saved successfully!");

            // Redirect back to the form page
            return "redirect:/leadRecords/showLeadRecordForm";
        } catch (Exception e) {
            e.printStackTrace();

            model.addAttribute("leadRecord", leadRecord);

            model.addAttribute("failedMessage", "Failed to save the Lead Record. Please try again.");

            return "leadRecords/lead-record-form";
        }
    }

    @PostMapping("/updateLeadRecord")
    public String updateLeadRecord(@ModelAttribute("leadRecord") LeadRecord leadRecord, Model model, RedirectAttributes redirectAttributes) {

        try{
            // Get the logged-in user
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            User user = usersService.findUserByUserName(username);

            // Set the user and save the lead record
            leadRecord.setUser(user);
            leadRecordService.update(leadRecord);

            redirectAttributes.addFlashAttribute("successMessage", "Successfully updated");

            // Redirect back to the form page
            return "redirect:/leadRecords/recordList";

        } catch (Exception e) {
            e.printStackTrace();

            model.addAttribute("leadRecord", leadRecord);

            model.addAttribute("errorMessage", "Update failed");

            return "leadRecords/update-form";
        }
    }

    @GetMapping("/recordList")
    public String showLeadRecordList(Model model, Principal principal) {
        User user = usersService.findUserByUserName(principal.getName());
        List<LeadRecord> leadRecords = leadRecordService.findByUserId(user.getId());
        model.addAttribute("leadRecord", leadRecords);
        return "leadRecords/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("leadRecordId") long id, Model model) {
        Optional<LeadRecord> leadRecord = leadRecordService.findById(id);
        model.addAttribute("leadRecord", leadRecord);
        return "leadRecords/update-form";
    }

    @GetMapping("/delete")
    public String deleteLeadRecord(@RequestParam("leadRecordId") long id) {
        leadRecordService.deleteByiD(id);
        return "redirect:/leadRecords/recordList";
    }

    @GetMapping("/navbar")
    public String showNavbar(){
        return "fragments/navbar";
    }
}
