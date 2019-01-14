package com.implicit.client.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequestMapping("/callback")
public class CallbackController {
    @RequestMapping(path="/onHandler",method=RequestMethod.GET)
    public ModelAndView onHandler(){
    	ModelAndView modelAndView =new ModelAndView("redirect");
    	return modelAndView;
    }
}
