package com.tinyurl.tinyserver.service;

import com.tinyurl.tinyserver.dao.GroupAdminRepository;
import com.tinyurl.tinyserver.dao.GroupRepository;
import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.model.Group;
import com.tinyurl.tinyserver.model.GroupAdmin;
import com.tinyurl.tinyserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        grpAdmin.setUserName(user.getUserName());
        grpAdmin.setUserId(user.getId());
        List<GroupAdmin> grpAdminList = new ArrayList<>();
        grpAdminList.add(grpAdmin);
        group.setGroupAdmins(grpAdminList);
        groupRepository.save(group);
        
    }

    @Override
    public List<Group> getAllGroups(Optional<User> user) {
        return groupRepository.findByUserId(user.get().getId());
    }

	@Override
	public void updateGroup(Group group, User user, int groupId) {
		// TODO Auto-generated method stub
		Optional<Group> tempGroup = groupRepository.findById(groupId);
		if(tempGroup.isPresent()){
			if(user.getId()==tempGroup.get().getUser().getId()){
				tempGroup.get().setGroupName(group.getGroupName());
				tempGroup.get().setGroupType(group.getGroupType());
				tempGroup.get().setGroupAdmin(group.getGroupAdmin());
				groupRepository.save(tempGroup.get());
			}
		}
	}

	@Override
	public void deleteGroup(User user, int groupId) {
		// TODO Auto-generated method stub
		List<GroupAdmin> groupAdminList= groupAdminRepository.findByGroupId(groupId);
		if(groupAdminList.size()>0){
			for(GroupAdmin grpAdmin:groupAdminList){
				if(user.getId()==grpAdmin.getUserId()){
					groupRepository.deleteById(groupId);
				}
			}
		}
	}

	@Override
	public String addGroupAdmin(User user, int groupId, int userId) {
		// TODO Auto-generated method stub
		if(!(groupAdminRepository.findByGroupIdAndUserId(groupId, userId).size()>0)){
			List<GroupAdmin> groupAdminList= groupAdminRepository.findByGroupId(groupId);
			if(groupAdminList.size()>0){
				for(GroupAdmin grpAdmin:groupAdminList){
					if(user.getId()==grpAdmin.getUserId()){
						GroupAdmin newGrpAdmin = new GroupAdmin();
						newGrpAdmin.setGroupId(groupId);
						newGrpAdmin.setUserId(userId);
						newGrpAdmin.setUserName(userRepository.findById(userId).get().getUserName());
						groupAdminRepository.save(newGrpAdmin);
						return "Success";
					}
				}
			}	
		}
		return null;
	}

	@Override
	public String deleteGroupAdmin(User user, int groupId, int userId) {
		// TODO Auto-generated method stub
		List<GroupAdmin> groupAdminList= groupAdminRepository.findByGroupId(groupId);
		if(groupAdminList.size()>=2){
			for(GroupAdmin grpAdmin:groupAdminList){
				if(user.getId()==grpAdmin.getUserId()){
					groupAdminRepository.delete(groupAdminRepository.findByGroupIdAndUserId(groupId, userId).get(0));
					return "Success";
				}
			}
		}
		return null;
	}

	@Override
	public Group getGroup(Optional<User> user, int groupId) {
		// TODO Auto-generated method stub
		return groupRepository.findById(groupId).get();
	}


}
