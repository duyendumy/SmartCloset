package com.group.smartcloset.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.smartcloset.model.User;
import com.group.smartcloset.model.Closet;
import com.group.smartcloset.model.Role;
import com.group.smartcloset.dto.UserRegistrationDto;
import com.group.smartcloset.repository.UserRepository;
import com.group.smartcloset.repository.ClosetRepository;
import com.group.smartcloset.repository.RoleRepository;
import java.time.LocalDateTime;  

@Service
public class UserDao {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ClosetRepository closetRepository;
	
	public UserDao(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	// Save User and User's Closet
	public User save(UserRegistrationDto userRegistrationDto){
		if(this.userRepository.getUserByEmail(userRegistrationDto.getEmail()) != null)
		{
			return null;
		}
		Date currentDate = new Date();
		Closet closet = this.closetRepository.save(new Closet());
		User user = new User(userRegistrationDto.getEmail(),
							closet.getId(), 
							userRegistrationDto.getUsername(),
							userRegistrationDto.getPassword(),
							currentDate,currentDate, 
							1, Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}
	
	
	public User getUser(UserRegistrationDto userRegistrationDto) {
		return userRepository.getUser(userRegistrationDto.getEmail(),userRegistrationDto.getPassword());
	}
	
	public List<User> getAllUsers(){	
		return userRepository.findAll();
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
}
