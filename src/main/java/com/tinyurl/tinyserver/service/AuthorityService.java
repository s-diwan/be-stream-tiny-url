package com.tinyurl.tinyserver.service;

import java.util.List;

import com.tinyurl.tinyserver.dto.ApprovalDto;
import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.GroupAdmin;
import com.tinyurl.tinyserver.model.User;

public interface AuthorityService {
	public void addApproval(Card card,int id, List<GroupAdmin> grpAdminList);
	public List<ApprovalDto> getMyApproval(User user);
	public void approve(int id);
	public void reject(int id);
}
