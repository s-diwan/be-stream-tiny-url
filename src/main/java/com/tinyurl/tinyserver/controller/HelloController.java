package com.tinyurl.tinyserver.controller;

import com.tinyurl.tinyserver.entity.User;
import com.tinyurl.tinyserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class HelloController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String helloWorld(){
        return "Hello World Controller New";
    }

    @PostMapping("/createUser")
    public void create(@RequestBody User user){
        userRepository.save(user);
    }


    @GetMapping("/user/{id}")
    public Optional<User> get(@PathVariable("id") int id){
       return userRepository.findById(id);
    }

}
