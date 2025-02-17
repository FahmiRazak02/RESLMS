package com.afzrealty.springboot.slmsystem.controller;

import com.afzrealty.springboot.slmsystem.dao.HouseListingRepository;
import com.afzrealty.springboot.slmsystem.entity.HouseListing;
import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.service.HouseListingService;
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

@Controller
@RequestMapping("/houseListing")
public class HouseListingController {
    private HouseListingService houseListingService;

    private UsersService userService;

    @Autowired
    public HouseListingController(HouseListingService houseListingService, UsersService usersService) {
        this.houseListingService = houseListingService;
        this.userService = usersService;
    }

    @GetMapping("/showForm")
    public String showForm(Model model) {
        HouseListing houseListing = new HouseListing();
        model.addAttribute("houseListings", houseListing);
        return "houseListing/house-form";
    }

    @PostMapping("/saveHouseListing")
    public String saveHouseListing(@ModelAttribute("houseListings") HouseListing houseListing,
                                   Model model, RedirectAttributes redirectAttributes) {
        try {
            // Debugging: Print the house listing object
            System.out.println(houseListing);

            // Get the logged-in user
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            User user = userService.findUserByUserName(username);

            // Set the user and save House Listing
            houseListing.setUser(user);
            houseListingService.save(houseListing);

            // Add flash attribute for success message
            redirectAttributes.addFlashAttribute("successMessage", "House Listing saved successfully!");

            // Redirect to the form after success
            return "redirect:/houseListing/showForm";
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();

            // Add the houseListing object back to the model
            model.addAttribute("houseListings", houseListing);

            // Add the error message
            model.addAttribute("errorMessage", "Failed to save the House Listing. Please try again.");

            // Return to the form page to retain the input
            return "houseListing/house-form"; // Adjust this to your actual form template name
        }
    }


    @PostMapping("/updateHouseListing")
    public String updateHouseListing(@ModelAttribute("houseListings") HouseListing houseListing, RedirectAttributes redirectAttributes) {
        // Debugging: Print the house listing object
        System.out.println(houseListing);

        // Get the logged-in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userService.findUserByUserName(username);

        // Set the user and save House Listing
        houseListing.setUser(user);
        houseListingService.save(houseListing);

        // Redirect back to the form page
        return "redirect:/houseListing/List ";
    }

    // Display House Listing Table
    @GetMapping("/List")
    public String getAllHouseListings(Model model, Principal principal) {
        User user = userService.findUserByUserName(principal.getName());
        List<HouseListing> houseListings = houseListingService.findHouseByUserId(user.getId());
        model.addAttribute("houseListings", houseListings);
        return "houseListing/house_listings";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("houseId") long id, Model model) {
        HouseListing houseListing = houseListingService.findById(id);
        model.addAttribute("houseListings", houseListing);
        return "houseListing/house-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("houseId") long id) {
        houseListingService.deleteById(id);
        return "redirect:/houseListing/List";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public HouseListing findOne(long id) {
        return houseListingService.findById(id);
    }
}
