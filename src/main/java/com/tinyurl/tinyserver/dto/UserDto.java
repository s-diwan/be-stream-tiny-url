package com.tinyurl.tinyserver.dto;

import lombok.Data;

@Data
public class UserDto {
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String password;
}
