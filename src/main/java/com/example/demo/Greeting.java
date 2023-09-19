package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Greeting {

    @GetMapping("/greeting")
    public String greet() {
        return "greeting_template";
    }

    @GetMapping("/hello")
    public String greet2() {
        return "greeting_template";
    }

}
