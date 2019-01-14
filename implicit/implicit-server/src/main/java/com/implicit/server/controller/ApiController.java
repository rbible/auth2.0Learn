package com.implicit.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @RequestMapping("/getinfo")
    public String getinfo(){
        return "info";
    }

}
