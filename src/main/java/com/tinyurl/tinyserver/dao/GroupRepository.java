package com.tinyurl.tinyserver.dao;



import com.tinyurl.tinyserver.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findByUserId(int userId);
    
	@Query("FROM Group ap JOIN ap.GroupAdmins apAd WHERE apAd.userId = :id")
	public List<Group> getMyGroup(@Param ("id") int id);
}
