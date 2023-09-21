package com.example.demo.user;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired UserService userService;
    
    @GetMapping("/users")
    public ResponseEntity<Object> getUsers(){

        Iterable<UserDto> users = this.userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody UserDto user){
        UserDto result = this.userService.createUser(user);
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto user, @PathVariable("id") Long id){
        UserDto result = this.userService.updateUser(user, id);
        return ResponseEntity.status(202).body(result);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Object> deleteUser(@RequestParam(name = "id", required = true) Long id){
        UserDto result = this.userService.deleteUser(id);
        return ResponseEntity.status(200).body(result);
    }
}
