package com.tinyurl.tinyserver.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tinyurl.tinyserver.model.User;

public interface UserService {
	public List<User> findAll();
	public Optional<User> get(int id);
	public void create(@Valid User user);
}
