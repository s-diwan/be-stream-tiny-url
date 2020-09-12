package com.tinyurl.tinyserver.controller;




import com.tinyurl.tinyserver.dao.GroupAdminRepository;
import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.User;
import com.tinyurl.tinyserver.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;


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
            System.out.println("PGID"+groupId);
            System.out.println("GIDUID "+groupAdminRepository.findByGroupIdAndUserId(groupId,userId).size());
            System.out.println("GID "+groupAdminRepository.findByGroupId(groupId).size());
            if(groupAdminRepository.findByGroupIdAndUserId(id,userId).size()>0 || groupAdminRepository.findByGroupId(id).size()==0) {
                cardService.createCard(card,id);
            }
        }
    }

}
