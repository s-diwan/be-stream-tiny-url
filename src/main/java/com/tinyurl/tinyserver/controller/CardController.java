package com.tinyurl.tinyserver.controller;




import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.tinyserver.dao.GroupAdminRepository;
import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.dto.DeleteCardDto;
import com.tinyurl.tinyserver.dto.UpdateCardDto;
import com.tinyurl.tinyserver.dto.UpdateCardResponseDto;
import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.GroupAdmin;
import com.tinyurl.tinyserver.model.User;
import com.tinyurl.tinyserver.service.AuthorityService;
import com.tinyurl.tinyserver.service.CardService;


@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    GroupAdminRepository groupAdminRepository;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AuthorityService authorityService;

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
    
    
    @PutMapping("/updateCard")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public UpdateCardResponseDto updateCard(@RequestBody UpdateCardDto card, Principal principal){
    	 Optional<User> user  = userRepository.findByUserName(principal.getName());
    	 UpdateCardResponseDto output = new UpdateCardResponseDto();
         if (user.isPresent()) {
        	 
        	 if(card.getGroup_id()==0 && card.getUserId()!=user.get().getId()){
          		
                
                  output.setMessage("You are not authorized to update this card");
                  return output;
          	}
         	if(card.getGroup_id()==0 && card.getUserId()==user.get().getId()){
         		int userId = user.get().getId();
                 cardService.upadteCardInUser(card, user.get());
                 output.setMessage("Updated the card as you are the card creator");
                 return output;
         	}
         	else{
         		 List<GroupAdmin> grpAdminList = groupAdminRepository.findByGroupId(card.getGroup_id());
         		 for(GroupAdmin grpAdmin : grpAdminList){
         			 if(grpAdmin.getUserId()==user.get().getId()){
         				 cardService.upadteCardInUser(card, user.get());
         				output.setMessage("Updated the card as you are a admin");
         				 return output;
         			 }
         			 else{
         				 authorityService.addApproval(card,user.get().getId(),grpAdminList);
         				 output.setMessage("You are not the card group admin so change request is in approval phase");
         				 return output;
         			 }
         		 }	
         	}
             
         }
         return null;
    }
    
    
    
    @PostMapping("/deleteCard/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public UpdateCardResponseDto deleteCard(@RequestBody DeleteCardDto card,@PathVariable("id") int cardId, Principal principal){
    	 Optional<User> user  = userRepository.findByUserName(principal.getName());
    	 UpdateCardResponseDto output = new UpdateCardResponseDto();
         if (user.isPresent()) {
        	 
        		if(card.getGroup_id()==0 && card.getUserId()!=user.get().getId()){
             		
                     output.setMessage("You are not authorized to delete this card");
                     return output;
             	}
         	if(card.getGroup_id()==0 && card.getUserId()==user.get().getId()){
         		int userId = user.get().getId();
                 cardService.deleteCardInUser(card, user.get());
                 output.setMessage("Deleted the card as you are the card creator");
                 return output;
         	}
         	else{
         		 List<GroupAdmin> grpAdminList = groupAdminRepository.findByGroupId(card.getGroup_id());
         		 for(GroupAdmin grpAdmin : grpAdminList){
         			 if(grpAdmin.getUserId()==user.get().getId()){
         				 cardService.deleteCardInUser(card, user.get());
         				  output.setMessage("Deleted the card as you are a admin");
                          return output;
         			 }
         			 else{
         				output.setMessage("You are not authorized to delete the card");
                        return output;
         			 }
         		 }	
         	}
             
         }
         return null;
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
