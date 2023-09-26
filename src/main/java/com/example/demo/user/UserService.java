package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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

    @Transactional
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = new UserEntity(user.getName(), user.getEmail());
        this.userRepository.save(userEntity);
        return user;
    }

    @Transactional
    public UserDto updateUser(UserDto user, Long id) {
        log.info("ID: " + id.toString());
        UserEntity userEntity = this.userRepository.findById(id).get();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        this.userRepository.save(userEntity);
        return user;
    }

    @Transactional
    public UserDto deleteUser(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            this.userRepository.delete(user.get());
            return new UserDto(user.get().getName(), user.get().getEmail());
        }else {
            return null;
        }
    }

}
