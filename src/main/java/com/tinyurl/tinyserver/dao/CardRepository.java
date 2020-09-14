package com.tinyurl.tinyserver.dao;


import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.GroupAdmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	List<Card> findByUserId(int userId);
}
