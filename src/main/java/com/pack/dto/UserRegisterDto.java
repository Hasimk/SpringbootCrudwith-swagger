package com.pack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
	
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;

}
