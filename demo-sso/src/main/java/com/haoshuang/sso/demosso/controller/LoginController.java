package com.haoshuang.sso.demosso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("login")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }
}
