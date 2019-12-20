package com.sample.tdddemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String sayHello(@RequestParam(defaultValue = "World",name="name") String firstName){
        return "Hello " + firstName + "!";
    }
}
