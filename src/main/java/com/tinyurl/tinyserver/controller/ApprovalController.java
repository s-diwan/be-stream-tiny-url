package com.tinyurl.tinyserver.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.tinyserver.dao.ApprovalRepository;
import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.dto.ApprovalDto;
import com.tinyurl.tinyserver.dto.MessageDto;
import com.tinyurl.tinyserver.model.Approval;
import com.tinyurl.tinyserver.model.User;
import com.tinyurl.tinyserver.service.AuthorityService;

@RestController
public class ApprovalController {
	
	 @Autowired
	 UserRepository userRepository;
	 
	 @Autowired 
	 AuthorityService authorityService;
	 
	 @Autowired
	 ApprovalRepository approvalRepository;

    @GetMapping("/getMyApprovals")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<ApprovalDto> getMyApprovals(Principal principal){
    	 Optional<User> user  = userRepository.findByUserName(principal.getName());
    	  if (user.isPresent()) {
    		  List<ApprovalDto> approvalList =  authorityService.getMyApproval(user.get());
    		  return approvalList.stream().filter(al -> al.getStatus().equals("Inapproval")).collect(Collectors.toList());
    	  }
    	  return null;
    }
    
    @GetMapping("/approve/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public MessageDto approve(@PathVariable("id") int id,Principal principal) throws Exception{
    	 Optional<User> user  = userRepository.findByUserName(principal.getName());
    	 MessageDto msg = new MessageDto();
    	  if (user.isPresent()) {
    		  Approval tempApproval =approvalRepository.findById(id).get();
    		  if(tempApproval.getStatus().equals("Inapproval")){
    			  authorityService.approve(id);
    			  msg.setMessage("Approved");
    			  return msg;
    		  }
    		  else{
    			  throw new Exception("Already Approved or rejected");
    		  }
    		  
    	  }
    	  return null;
    }
    
    @GetMapping("/reject/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public MessageDto reject(@PathVariable("id") int id,Principal principal) throws Exception{
    	 Optional<User> user  = userRepository.findByUserName(principal.getName());
    	 MessageDto msg = new MessageDto();
    	  if (user.isPresent()) {
    		 Approval tempApproval =approvalRepository.findById(id).get();
    		 if(tempApproval.getStatus().equals("Inapproval")){
    		 authorityService.reject(id);
    		 msg.setMessage("Reject");
			  return msg;
    		 }
    		 else{
   			  throw new Exception("Already Approved or rejected");
   		  }
    	  }
    	  return null;
    }
}
