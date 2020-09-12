package com.tinyurl.tinyserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO ,generator="user_seq")
	private int id;
	
	@NotEmpty
	@NotBlank(message = "FirstName is mandatory")
	private String firstName;
	
	private String lastName;
	private String gender;
	private String userName;
	
	@NotEmpty(message = "Email field should not be empty")
	@Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
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