package com.tinyurl.tinyserver.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tinyurl.tinyserver.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	List<Card> findByUserId(int userId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Card ap WHERE ap.group_id = :id")
	void deleteByGroupId(@Param ("id") int id);
}
