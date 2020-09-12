package com.tinyurl.tinyserver.service;

import com.tinyurl.tinyserver.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	public List<User> findAll();
	public Optional<User> get(int id);
	public void create(User user);
}
