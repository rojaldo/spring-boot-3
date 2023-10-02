package com.example.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import java.util.HashMap;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HashMap<String, String> handleException(Exception e) {
        return new HashMap<String, String>() {
            {
                put("message", e.getMessage());
            }
        };
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public HashMap<String, String> handleInternalServerErrorServerException(InternalServerError e) {
        return new HashMap<String, String>() {
            {
                put("message", e.getMessage());
            }
        };
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public HashMap<String, String> handleNotFoundException(NotFoundException e) {
        return new HashMap<String, String>() {
            {
                put("message", e.getMessage());
            }
        };
    }

    
}
