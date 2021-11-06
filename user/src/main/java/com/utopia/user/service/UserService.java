package com.utopia.user.service;

import java.util.List;

import com.utopia.user.dto.UserDto;
import com.utopia.user.entity.Users;

public interface UserService {
	
	List<UserDto> getAllUsers();
	List<UserDto> getUserById(Long id);
	String updateUser(UserDto userInfo);
	
}
