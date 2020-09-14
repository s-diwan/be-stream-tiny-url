package com.tinyurl.tinyserver.controller;


import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.dto.GroupDto;
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
    public void create(@RequestBody GroupDto groupDto, Principal principal){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            user.ifPresent(userData -> {
            	Group group = new Group();
            	group.setGroupName(groupDto.getGroupName());
            	group.setGroupType(groupDto.getGroupType());
            	group.setGroupAdmin(user.get().getUserName());
                groupService.create(group,userData);
            });
        }
    }
    
    @PutMapping("/updateGroup/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void update(@RequestBody GroupDto groupDto, Principal principal,@PathVariable("id") int id){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            user.ifPresent(userData -> { 
            	Group group = new Group();
            	group.setGroupName(groupDto.getGroupName());
            	group.setGroupType(groupDto.getGroupType());
            	group.setGroupAdmin(user.get().getUserName());
                groupService.updateGroup(group,userData,id);
            });
        }
    }
    
    @DeleteMapping("/deleteGroup/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void deleteGroup(Principal principal,@PathVariable("id") int id){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            user.ifPresent(userData -> { 
                groupService.deleteGroup(userData, id);
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
    
    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Group getGroup(Principal principal,@PathVariable("id") int groupId){
        Optional<User> user  = userRepository.findByUserName(principal.getName());

        if (user.isPresent()) {
           return  groupService.getGroup(user, groupId);
        }
        return  null;
    }
    
    
    @PostMapping("/addGroupAdmin/{groupId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void addGroupAdmin(Principal principal,@PathVariable("groupId") int groupId,@PathVariable("userId") int userId){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            user.ifPresent(userData -> { 
                groupService.addGroupAdmin(userData, groupId,userId);
            });
        }
    }
    
    @DeleteMapping("/deleteGroupAdmin/{groupId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void deleteGroupAdmin(Principal principal,@PathVariable("groupId") int groupId,@PathVariable("userId") int userId){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            user.ifPresent(userData -> { 
                groupService.deleteGroupAdmin(userData, groupId,userId);
            });
        }
    }
}
