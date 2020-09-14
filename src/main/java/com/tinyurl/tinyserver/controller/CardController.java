package com.tinyurl.tinyserver.controller;




import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.tinyserver.dao.GroupAdminRepository;
import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.User;
import com.tinyurl.tinyserver.service.CardService;


@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    GroupAdminRepository groupAdminRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/createCard/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void create(@RequestBody Card card, Principal principal, @PathVariable int id){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            int userId = user.get().getId();
            int groupId = card.getGroup_id();
            if(groupAdminRepository.findByGroupIdAndUserId(id,userId).size()>0 || groupAdminRepository.findByGroupId(id).size()==0) {
                cardService.createCardInGroup(card,id,user.get());
            }
        }
    }
    
    
    @PostMapping("/createCard")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void createCard(@RequestBody Card card, Principal principal){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            int userId = user.get().getId();
            cardService.createCardInUser(card, user.get());
        }
    }
    
    @GetMapping("/getAllCards")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<Card> getAllCards(Principal principal){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            return cardService.getAllCards();
        }
        return null;
    }
    
    @GetMapping("/getMyCards")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<Card> getMyCards(Principal principal){
        Optional<User> user  = userRepository.findByUserName(principal.getName());
        if (user.isPresent()) {
            return cardService.getMyCards(user.get());
        }
        return null;
    }
}
