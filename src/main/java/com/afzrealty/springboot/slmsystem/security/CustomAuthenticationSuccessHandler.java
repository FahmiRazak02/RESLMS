package com.afzrealty.springboot.slmsystem.security;

import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.service.UsersService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private UsersService usersService;

    public CustomAuthenticationSuccessHandler(UsersService theUsersService){
        usersService = theUsersService;
    }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {

            System.out.println("In customAuthenticationSuccessHandler");

            String userName = authentication.getName();

            System.out.println("userName" + userName);

            User theUser = usersService.findUserByUserName(userName);

            // now place in the session
            HttpSession session = request.getSession();
            session.setAttribute("agents", theUser);

            // forward to home page
            response.sendRedirect(request.getContextPath() + "/");
        }

}

