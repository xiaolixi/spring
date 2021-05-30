package com.tom.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @GetMapping("/test")
    public String str(){
        return UUID.randomUUID().toString();
    }
}
