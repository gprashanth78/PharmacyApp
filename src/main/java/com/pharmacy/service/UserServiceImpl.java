package com.pharmacy.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pharmacy.entity.Role;
import com.pharmacy.entity.User;
import com.pharmacy.entity.UserDto;
import com.pharmacy.repository.RoleRepository;
import com.pharmacy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public User saveUser(UserDto userDto) {
		logger.info("Saving user with username: {}", userDto.getUsername());
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());

		List<Role> roles = new ArrayList<>();
		logger.info("Fetching role with name: {}", userDto.getRoleName());
		Role role = roleRepository.findByName(userDto.getRoleName());
		if (role == null) {
			logger.error("Role not found: {}", userDto.getRoleName());
			throw new RuntimeException("Role not found: " + userDto.getRoleName());
		}
		logger.info("Role found: {}", role);
		roles.add(role);
		user.setRoles(roles);
		return userRepository.save(user);
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

}
