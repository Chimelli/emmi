package com.github.chimelli.emmi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmmiController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}