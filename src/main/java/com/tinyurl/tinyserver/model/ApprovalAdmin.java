package com.tinyurl.tinyserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "approval_admin")
@Data
public class ApprovalAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="approval_admin_seq")
    private int id;
    private String userName;
    private int userId;
    private int grpId;
    @Column(name = "approval_id")
    private int approvalId;
    
}
