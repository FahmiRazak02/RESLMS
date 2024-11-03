package com.afzrealty.springboot.slmsystem.controller;

import com.afzrealty.springboot.slmsystem.entity.FollowUp;
import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.service.FollowUpService;
import com.afzrealty.springboot.slmsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/followup")
public class FollowupController {

    private UsersService usersService;
    private FollowUpService followUpService;

    @Autowired
    public FollowupController(UsersService usersService, FollowUpService followUpService){
        this.usersService = usersService;
        this.followUpService = followUpService;
    }
    @GetMapping("schedule")
    public String showFirstStageList(Model model, Principal principal) {
        User user = usersService.findUserByUserName(principal.getName());
        // Get the follow-up records by user ID and stages
        List<FollowUp> followUps = followUpService.findByIdAndStage(user.getId(), "First");
        // Add the follow-up records to the model
        model.addAttribute("followUps", followUps);
        // Return the view name
        return "followup/list";
    }

    @GetMapping("schedule2nd")
    public String showSecondStageList(Model model, Principal principal) {
        User user = usersService.findUserByUserName(principal.getName());
        // Get the follow-up records by user ID and stages
        List<FollowUp> followUps = followUpService.findByIdAndStage(user.getId(), "Second");
        // Add the follow-up records to the model
        model.addAttribute("followUps", followUps);
        // Return the view name
        return "followup/list_2nd";
    }

    @GetMapping("schedule3rd")
    public String showThirdStageList(Model model, Principal principal) {
        User user = usersService.findUserByUserName(principal.getName());
        // Get the follow-up records by user ID and stages
        List<FollowUp> followUps = followUpService.findByIdAndStage(user.getId(), "Third");
        // Add the follow-up records to the model
        model.addAttribute("followUps", followUps);
        // Return the view name
        return "followup/list_3rd";
    }

    @PostMapping("secondStage/{id}")
    public String getSecondStage(@PathVariable Long id){
        followUpService.updateSecondStage(id);
        return "redirect:/followup/schedule2nd";
    }

    @PostMapping("thirdStage/{id}")
    public String getThirdStage(@PathVariable Long id){
        followUpService.updateThirdStage(id);
        return "redirect:/followup/schedule3rd";
    }

    @PostMapping("delete/{id}")
    public String deleteScheduleById(@PathVariable Long id){
        followUpService.deleteById(id);
        return "redirect:/followup/schedule3rd";
    }
}
