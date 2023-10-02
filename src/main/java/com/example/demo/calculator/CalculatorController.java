package com.example.demo.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

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

    @GetMapping("/evaluate")
    public String eval(
        @RequestParam(name = "exp", required = false, defaultValue = "1+2=") String expression,
        Model view
    ){
        String message = this.calculatorService.evaluate(expression);
        view.addAttribute("message", message);
        return "calculator_template";
    }



}
