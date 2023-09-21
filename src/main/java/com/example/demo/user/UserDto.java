package com.example.demo.user;

import io.micrometer.common.lang.NonNull;

public class UserDto {

    @NonNull
    private String name;

    @NonNull
    private String email;

    public UserDto() {
    }

    public UserDto(@NonNull String name, @NonNull String email) {
        this.name = name;
        this.email = email;
    }
    
}
