package com.pharmacy.service;

import java.util.List;

import com.pharmacy.entity.User;
import com.pharmacy.entity.UserDto;

public interface UserService {

	public User saveUser(UserDto userDto);

	public User findByUserName(String userName);

	public List<User> findAllUsers();
}
