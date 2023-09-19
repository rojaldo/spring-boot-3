package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Greeting {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/greeting")
    public String greet(Model view) {
        view.addAttribute("message", "Hello World");
        return "greeting_template";
    }

    @GetMapping("/echo")
    public String echo(
            @RequestParam(name = "msg", required = false, defaultValue = "Hello World") String message,
            Model view) {
        view.addAttribute("message", message);
        return "greeting_template";
    }

    @GetMapping("/calculator")
    public String calculator(
            @RequestParam(name = "op1", required = false, defaultValue = "0") float op1,
            @RequestParam(name = "op2", required = false, defaultValue = "0") float op2,
            @RequestParam(name = "operator", required = false, defaultValue = "+") String operator,
            Model view) {
        float result = this.calculatorService.calculate(op1, op2, operator);
        String message = op1 + " " + operator + " " + op2 + " = " + result;
        view.addAttribute("message", message);
        return "calculator_template";
    }



}
