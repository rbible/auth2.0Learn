package com.implicit.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/callback")
public class CallbackController {
    @RequestMapping(path="/redirect",method=RequestMethod.GET)
    public ModelAndView redirect(){
    	ModelAndView modelAndView =new ModelAndView("redirect");
    	return modelAndView;
    }
}
