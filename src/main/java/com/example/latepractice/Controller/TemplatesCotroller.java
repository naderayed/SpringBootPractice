package com.example.latepractice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplatesCotroller {

    @GetMapping("login")
    public String getLoginView(){
        return "loginPage";
    }
}
