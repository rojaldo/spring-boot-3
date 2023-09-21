package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<UserDto> getUsers() {
        Iterable<UserEntity> users = this.userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity user : users) {
            UserDto userDto = new UserDto(user.getName(), user.getEmail());
            userDtos.add(userDto);
        }
        return userDtos;
    }
    
}
