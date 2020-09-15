package com.tinyurl.tinyserver.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="approval_table")
@Data
public class Approval {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO , generator="approval_sequence" )
	  private int id;
	  private String url;
	  private String title;
	  private String description;
	  private String cardType;
	  private int cardId;
	  private int grpId;
	  private String status;
	  private int requestedBy;
	  @OneToMany(targetEntity=ApprovalAdmin.class,cascade = CascadeType.ALL)
	  @JoinColumn(name = "approval_id", referencedColumnName="id")
	  private List<ApprovalAdmin> approvalAdmins;
}
