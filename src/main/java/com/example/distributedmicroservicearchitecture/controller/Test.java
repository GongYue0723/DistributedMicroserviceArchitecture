package com.example.distributedmicroservicearchitecture.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping(value = "/hello")
    public String test (){
        return "Hello World!";
    }
}
