package com.tinyurl.tinyserver.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tinyurl.tinyserver.dto.ApprovalDto;
import com.tinyurl.tinyserver.model.Approval;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Integer>{
	
	
	
	@Query("SELECT new com.tinyurl.tinyserver.dto.ApprovalDto(ap.id,ap.cardId,ap.title,ap.description,ap.cardType,ap.requestedBy,ap.status) FROM Approval ap JOIN ap.approvalAdmins apAd WHERE apAd.userId = :id")
	public List<ApprovalDto> getMyApprovals(@Param ("id") int id);
	
}
