package com.tinyurl.tinyserver.dao;

import java.util.Optional;


import com.tinyurl.tinyserver.model.MyUserDetails;
import com.tinyurl.tinyserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;






@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user =userRepository.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found: "+username));
		return user.map(MyUserDetails::new).get();
	}
}
