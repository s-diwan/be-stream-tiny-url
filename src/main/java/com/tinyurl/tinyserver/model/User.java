package com.tinyurl.tinyserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO ,generator="user_seq")
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private String userName; 
	private String email;
	
	private String password;
	private boolean active;
	private String roles;

	@OneToMany(mappedBy = "user" ,cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH , CascadeType.REFRESH})
	@JsonManagedReference
	private List<Group> groups;

	// bi-directional relationship
	public void add(Group group){
		if(groups==null){
			groups = new ArrayList<>();
		}
		groups.add(group);
		group.setUser(this);
	}

}