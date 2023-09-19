package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Greeting {

    @GetMapping("/greeting")
    public String greet(Model view) {
        view.addAttribute("message", "Hello World");
        return "greeting_template";
    }

    @GetMapping("/echo")
    public String echo(
        @RequestParam(name="msg", required=false, defaultValue="Hello World") String message,
        Model view) {
        view.addAttribute("message", message);
        return "greeting_template";
    }


}
