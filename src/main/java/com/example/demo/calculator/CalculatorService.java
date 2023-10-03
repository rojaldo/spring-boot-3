package com.example.demo.calculator;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

enum State {
    INIT, FIRST_FIGURE, SECOND_FIGURE, ERROR
}

@Service
@Slf4j
public class CalculatorService {

    State currentState = State.INIT;
    int op1 = 0;
    int op2 = 0;
    String operator = "";
    float result = 0;

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

    public String evaluate(String expression) {
        log.info("evaluate: {}", expression);
        if (expression == null || expression.length() == 0) {
            return " => ERROR";
        }
        String resultString = "";
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                this.processNumber(Character.getNumericValue(c));
            } else {
                this.processSymbol(c);
            }
        }
        if (this.currentState == State.INIT) {
            resultString = String.valueOf(this.op1) + String.valueOf(this.operator) + String.valueOf(this.op2) + "="
                    + String.valueOf(this.result);
        } else {
            resultString = expression + " => ERROR";
        }
        this.clearCalculator();
        return resultString;
    }

    void clearCalculator() {
        this.currentState = State.INIT;
        this.op1 = 0;
        this.op2 = 0;
        this.operator = "";
        this.result = 0;
    }

    private void processNumber(int number) {
        switch (this.currentState) {
            case INIT:
            this.op1 = number;
            this.currentState = State.FIRST_FIGURE;
                break;
            case FIRST_FIGURE:
            this.op1 = this.op1 * 10 + number;
                break;
            case SECOND_FIGURE:
            this.op2 = this.op2 * 10 + number;
                break;
            case ERROR:

                break;

            default:
                break;
        }

    }

    private void processSymbol(char c) {
        switch(this.currentState){
            case INIT:
            this.currentState = State.ERROR;
                break;
            case FIRST_FIGURE:
            if(this.isOperator(c)){
                this.operator = String.valueOf(c);
                this.currentState = State.SECOND_FIGURE;
            }else {
                this.currentState = State.ERROR;
            }
                break;
            case SECOND_FIGURE:
            if(c == '='){
                this.result = this.calculate();
                this.currentState = State.INIT;
            }
            else {
                this.currentState = State.ERROR;
            }
                break;
            case ERROR:

                break;
            default:
                break;
        }

    }

    private float calculate() {
        return this.calculate((float) this.op1, (float) this.op2, this.operator);
    }

    boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

}
