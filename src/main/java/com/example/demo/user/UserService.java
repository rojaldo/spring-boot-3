package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

    public UserDto createUser(UserDto user) {
        UserEntity userEntity = new UserEntity(user.getName(), user.getEmail());
        this.userRepository.save(userEntity);
        return user;
    }

    public UserDto updateUser(UserDto user, Long id) {
        log.info("ID: "+ id.toString());
        UserEntity userEntity = this.userRepository.findById(id).get();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        this.userRepository.save(userEntity);
        return user;
    }

    public UserDto deleteUser(Long id) {
        UserEntity userEntity = this.userRepository.findById(id).get();
        this.userRepository.delete(userEntity);
        return new UserDto(userEntity.getName(), userEntity.getEmail());
    }
    
}
