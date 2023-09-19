package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public float calculate(float op1, float op2, String operator) {
        float result = 0;
        switch (operator) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                return op1 / op2;
            default:
                break;
        }
        return result;
    }
    
}
