package com.tinyurl.tinyserver.dao;


import com.tinyurl.tinyserver.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findByUserId(int userId);
}
