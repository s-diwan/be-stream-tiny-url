package com.tinyurl.tinyserver.service;

import com.tinyurl.tinyserver.dao.GroupAdminRepository;
import com.tinyurl.tinyserver.dao.GroupRepository;
import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.model.Group;
import com.tinyurl.tinyserver.model.GroupAdmin;
import com.tinyurl.tinyserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GroupServiceImpl implements  GroupService{

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupAdminRepository groupAdminRepository;

    @Override
    public void create(Group group, User user) {
        user.add(group);
        GroupAdmin grpAdmin = new GroupAdmin();
        grpAdmin.setId(201);
        grpAdmin.setGroupId(group.getId());
        grpAdmin.setUserName(user.getUserName());
        grpAdmin.setUserId(user.getId());
        groupRepository.save(group);
        groupAdminRepository.save(grpAdmin);
    }

    @Override
    public List<Group> getAllGroups(Optional<User> user) {
        return groupRepository.findByUserId(user.get().getId());
    }
}
