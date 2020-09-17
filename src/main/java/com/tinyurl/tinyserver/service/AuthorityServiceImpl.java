package com.tinyurl.tinyserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinyurl.tinyserver.dao.ApprovalRepository;
import com.tinyurl.tinyserver.dao.CardRepository;
import com.tinyurl.tinyserver.dto.ApprovalDto;
import com.tinyurl.tinyserver.dto.UpdateCardDto;
import com.tinyurl.tinyserver.model.Approval;
import com.tinyurl.tinyserver.model.ApprovalAdmin;
import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.GroupAdmin;
import com.tinyurl.tinyserver.model.User;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	ApprovalRepository approvalRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	@Override
	public void addApproval(UpdateCardDto card,int id, List<GroupAdmin> grpAdminList) {
		// TODO Auto-generated method stub
		Approval tempApproval = new Approval();
		tempApproval.setUrl(card.getUrl());
		tempApproval.setTitle(card.getTitle());
		tempApproval.setDescription(card.getDescription());
		tempApproval.setCardType(card.getCardType());
		tempApproval.setCardId(card.getId());
		tempApproval.setGrpId(card.getGroup_id());
		tempApproval.setStatus("Inapproval");
		tempApproval.setRequestedBy(id);
		List<ApprovalAdmin> approvalAdminList = new ArrayList<>();
		for(GroupAdmin grpAdmin:grpAdminList){
			ApprovalAdmin aprvlAdmin = new ApprovalAdmin();
			aprvlAdmin.setUserName(grpAdmin.getUserName());
			aprvlAdmin.setUserId(grpAdmin.getUserId());
			aprvlAdmin.setGrpId(grpAdmin.getGroupId());
			approvalAdminList.add(aprvlAdmin);
		}
		tempApproval.setApprovalAdmins(approvalAdminList);
		approvalRepository.save(tempApproval);
	}

	@Override
	public void approve(int id) {
		// TODO Auto-generated method stub
		Approval tempApproval =approvalRepository.findById(id).get();
		tempApproval.setStatus("Approved");
		Optional<Card> tempCard =cardRepository.findById(tempApproval.getCardId());
		if(tempCard.isPresent()){
			tempCard.get().setTitle(tempApproval.getTitle());
			tempCard.get().setDescription(tempApproval.getDescription());
			tempCard.get().setCardType(tempApproval.getCardType());
			cardRepository.save(tempCard.get());
		}
		approvalRepository.save(tempApproval);
	}

	@Override
	public void reject(int id) {
		// TODO Auto-generated method stub
		Approval tempApproval =approvalRepository.findById(id).get();
		tempApproval.setStatus("Rejected");
		approvalRepository.save(tempApproval);
		
	}

	@Override
	public List<ApprovalDto> getMyApproval(User user) {
		// TODO Auto-generated method stub	
		return approvalRepository.getMyApprovals(user.getId());
	}

}
