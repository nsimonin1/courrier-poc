package org.poc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by simon on 20/08/2016.
 */
@Controller
@RequestMapping({"/"})
public class HomeController {
    @RequestMapping()
    public String homePage(){
        return "index";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }
}
