package com.example.demo.user;

public class UserErrorResponse implements IUserResponse {

    private int status;
    private String message;

    public UserErrorResponse() {
    }

    public UserErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return this.status;
    }
    
}
