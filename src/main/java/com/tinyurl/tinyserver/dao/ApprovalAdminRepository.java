package com.tinyurl.tinyserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tinyurl.tinyserver.model.ApprovalAdmin;


@Repository
public interface ApprovalAdminRepository extends JpaRepository<ApprovalAdmin, Integer> {

}
