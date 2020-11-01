package com.ju.gateway.controller;

import com.ju.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping("getAuths/{userId}")
    public  Object getAuto(@PathVariable String userId) {
        return authService.getAuths(Integer.parseInt(userId));
    }
}
