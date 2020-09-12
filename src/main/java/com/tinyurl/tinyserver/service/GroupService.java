package com.tinyurl.tinyserver.service;


import com.tinyurl.tinyserver.model.Group;
import com.tinyurl.tinyserver.model.User;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    public void create(Group group, User user);
    public void updateGroup(Group group, User user,int groupId);
    public void deleteGroup(User user,int groupId);
    public List<Group> getAllGroups(Optional<User> user);
    public void addGroupAdmin(User user,int groupId,int userId);
    public void deleteGroupAdmin(User user,int groupId,int userId);
}
