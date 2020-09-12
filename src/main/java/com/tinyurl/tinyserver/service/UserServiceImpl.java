package com.tinyurl.tinyserver.service;

import com.tinyurl.tinyserver.dao.UserRepository;
import com.tinyurl.tinyserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<User> get(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

}
