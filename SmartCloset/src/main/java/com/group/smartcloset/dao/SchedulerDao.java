package com.group.smartcloset.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.smartcloset.dto.SchedulerDto;
import com.group.smartcloset.model.Closet;
import com.group.smartcloset.model.Outfit;
import com.group.smartcloset.model.Scheduler;
import com.group.smartcloset.repository.ClosetRepository;
import com.group.smartcloset.repository.SchedulerRepository;
import com.group.smartcloset.repository.OutfitRepository;


@Service
public class SchedulerDao {
	
	@Autowired
	private SchedulerRepository schedulerRepository;
	
	@Autowired
	private ClosetRepository closetRepository;
	
	@Autowired
	private OutfitRepository outfitRepository;

	
	
	public SchedulerDao(SchedulerRepository schedulerRepository) {
		super();
		this.schedulerRepository = schedulerRepository;
	}

	public Scheduler save(SchedulerDto schedulerDto) {
		Scheduler scheduler = new Scheduler();
		Closet closet = closetRepository.getClosetById(schedulerDto.getClosetId());
		scheduler.setCloset(closet);
		Outfit outfit = outfitRepository.getOutfitById(schedulerDto.getOutfitId());
		scheduler.setOutfit(outfit);
		scheduler.setSelectedDate(schedulerDto.getSelectedDate());
		return this.schedulerRepository.save(scheduler);

	}
	
	 public List<Scheduler> getAllSchedulers(Long closetId) {
		 Closet closet = closetRepository.getClosetById(closetId);
		 return schedulerRepository.getAllSchedulers(closet);
	}
	 
	 public Long getCountScheduler(Long idCloset) {
		 Closet closet = closetRepository.getClosetById(idCloset);
		 return schedulerRepository.getCountScheduler(closet);
	 }

	
}
