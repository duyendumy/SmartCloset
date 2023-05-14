package com.group.smartcloset.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group.smartcloset.model.User;
import com.group.smartcloset.dao.UserDao;
import com.group.smartcloset.dto.UserRegistrationDto;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	
	@PostMapping("/user/save")
	public User save(@RequestBody UserRegistrationDto userRegistrationDto) {
		return userDao.save(userRegistrationDto);
	}
	


	@GetMapping("/user/get-all")
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	@PostMapping("/user/get-user")
	public User ẻ(@RequestBody UserRegistrationDto userRegistrationDto) {
		return userDao.getUser(userRegistrationDto);
	}
	
	
//	@PostMapping("/user/save")
//	public User save(@RequestBody User user) {
//		return userDao.save(user);
//	}
}
