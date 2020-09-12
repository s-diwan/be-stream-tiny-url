package com.tinyurl.tinyserver.service;


import com.tinyurl.tinyserver.model.Group;
import com.tinyurl.tinyserver.model.User;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    public void create(Group group, User user);
    public List<Group> getAllGroups(Optional<User> user);
}
