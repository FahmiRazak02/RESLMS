package com.afzrealty.springboot.slmsystem.controller;

import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.service.UsersService;
import com.afzrealty.springboot.slmsystem.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/userAccount")
public class ManageUserAccountController {

    private UsersService usersService;
    @Autowired
    public ManageUserAccountController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("manage")
    public String manageAccounts(Model model){
        List<User> users = usersService.findAll();
        model.addAttribute("users", users);
        return "userAccount/manage";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") Long userId, Model model) {
        User user = usersService.findById(userId);
        model.addAttribute("user", user);
        return "userAccount/user-form";
    }

    @PostMapping("/update/{userId}")
    public String updateUser(@PathVariable Long userId, @ModelAttribute WebUser webUser) {
        usersService.update(webUser, userId);
        return "redirect:/userAccount/manage";
    }

    @PostMapping("approve/{userId}")
    public String approveAccount(@PathVariable Long userId) {
        usersService.approved(userId);
        return "redirect:/userAccount/manage";
    }

    @PostMapping("disapprove/{userId}")
    public String disapproveAccount(@PathVariable Long userId) {
        usersService.disapprove(userId);
        return "redirect:/userAccount/manage";
    }

    @GetMapping("/delete")
    public String deleteUserAccount(@RequestParam("userId") long userId) {
        usersService.deleteById(userId);
        return "redirect:/userAccount/manage";
    }
}
