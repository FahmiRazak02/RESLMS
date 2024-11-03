package com.afzrealty.springboot.slmsystem.controller;

import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.service.UsersService;
import com.afzrealty.springboot.slmsystem.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(getClass().getName());
    private final UsersService usersService;

    @Autowired
    public RegistrationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel) {
        theModel.addAttribute("webUser", new WebUser());
        return "register/registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") WebUser theWebUser,
            BindingResult theBindingResult,
            HttpSession session, Model theModel) {

        String userName = theWebUser.getUserName();
        String nric = theWebUser.getNric();
        String email = theWebUser.getEmail();

        logger.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()) {
            return "register/registration-form";
        }

        // check the database if user already exists
        User existing = usersService.findUserByUserName(userName);
        User existingUserByNric = usersService.findUserByNric(nric);
        User existingUserByEmail = usersService.findUserByEmail(email);

        if (existing != null) {
            theModel.addAttribute("registrationError", "User name already exists.");
            logger.warning("User name already exists.");
            return "register/registration-form";
        }

        if (existingUserByNric != null) {
            theModel.addAttribute("registrationError", "NRIC already exists.");
            logger.warning("NRIC already exists.");
            return "register/registration-form";
        }

        if (existingUserByEmail != null) {
            theModel.addAttribute("registrationError", "Email already exists.");
            logger.warning("Email already exists.");
            return "register/registration-form";
        }

        // create user account and store in the database
        usersService.save(theWebUser);

        logger.info("Successfully created user: " + userName);

        // place user in the web http session for later use
        session.setAttribute("agents", theWebUser);

        return "register/registration-confirmation";
    }
}
