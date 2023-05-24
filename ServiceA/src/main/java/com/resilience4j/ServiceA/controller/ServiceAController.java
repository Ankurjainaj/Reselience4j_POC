package com.resilience4j.ServiceA.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b")
public class ServiceAController {

    @GetMapping()
    public String serviceB(){
        return "Service B is called from Service A";
    }
}
