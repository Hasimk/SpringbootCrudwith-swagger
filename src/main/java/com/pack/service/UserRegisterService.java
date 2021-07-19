package com.pack.service;

import java.util.List;

import com.pack.dto.UserRegisterDto;

public interface UserRegisterService {
	
	
UserRegisterDto getuserByid(Integer userId);
	
List<UserRegisterDto> getAllUser();

//Page<UserRegisterDto> getAllUser();
	UserRegisterDto addNewUser(UserRegisterDto userRegisterDto);

	void updateUser(UserRegisterDto userRegisterDto);

	void deleteUser(Integer userId);
	

}
