package com.tablereservations.project.demo_table_reservations.controller;

import com.tablereservations.project.demo_table_reservations.dto.UserSecurityDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {
    //auth/login
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpServletRequest request){
        HttpSession userSession = request.getSession();
        UserDetails userDetails = (UserDetails)
                SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
        UserSecurityDto securityDto = (UserSecurityDto)userDetails;
        userSession.setAttribute("nomusuario",
                securityDto.getEmail());
        return "auth/home";
    }
}
