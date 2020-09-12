package com.tinyurl.tinyserver.dao;


import com.tinyurl.tinyserver.model.GroupAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupAdminRepository extends JpaRepository<GroupAdmin,Integer> {
    List<GroupAdmin> findByGroupIdAndUserId(int groupId,int userId);
    List<GroupAdmin> findByGroupId(int groupId
    );
}
