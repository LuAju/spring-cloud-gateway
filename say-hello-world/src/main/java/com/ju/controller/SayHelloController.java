package com.ju.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {
    @RequestMapping("/baidu")
    public Object get(){
        return "8082 sayHello";
    }
}
