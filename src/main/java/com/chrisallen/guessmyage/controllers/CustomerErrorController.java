package com.chrisallen.guessmyage.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerErrorController {

    @GetMapping
    public String customErrorPage(){
        return "/error";
    }

}
