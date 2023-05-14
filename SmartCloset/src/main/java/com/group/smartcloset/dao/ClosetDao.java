package com.group.smartcloset.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.Closet;
import com.group.smartcloset.repository.ClosetRepository;
import com.group.smartcloset.repository.UserRepository;

@Service
public class ClosetDao {

	@Autowired
	private ClosetRepository closetRepository;
	@Autowired
	private UserRepository userRepository;

	public ClosetDao(ClosetRepository closetRepository) {
		super();
		this.closetRepository = closetRepository;
	}
	
	public Closet getClosetById(Long id) {
		return closetRepository.getClosetById(id);
	}
	
	public Closet save(Closet closet) {
		return closetRepository.save(closet);
	}
	
}
