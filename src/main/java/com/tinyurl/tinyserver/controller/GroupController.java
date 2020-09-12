package com.tinyurl.tinyserver.controller;


import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.model.Group;
import com.tinyurl.tinyserver.model.User;
import com.tinyurl.tinyserver.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/createGroup")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void create(@RequestBody Group group, Principal principal){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            user.ifPresent(userData -> {
                groupService.create(group,userData);
            });
        }
    }

    @GetMapping("/getAllGroups")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<Group> getAllGroups(Principal principal){
        Optional<User> user  = userRepository.findByUserName(principal.getName());

        if (user.isPresent()) {
           return  groupService.getAllGroups(user);
        }
        return  null;
    }
}
