package ru.vtb.slepenkov.datamanager.configuration;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation 
 */
@Hidden
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        System.out.println("/swagger-ui/index.html");
        return "redirect:/swagger-ui/";
    }
}
