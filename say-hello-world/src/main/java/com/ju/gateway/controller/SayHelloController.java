package com.ju.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {
    @RequestMapping("/baidu")
    public Object get(){
        return "8082 sayHello";
    }

    @RequestMapping("/lookupInfo")
    public Object lookupInfo(){
        return "8082 lookupInfo";
    }

    @RequestMapping("/help")
    public Object help(){
        return "8082 help";
    }
}
