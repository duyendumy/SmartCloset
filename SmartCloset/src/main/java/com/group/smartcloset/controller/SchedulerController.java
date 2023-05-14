package com.group.smartcloset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group.smartcloset.dao.SchedulerDao;
import com.group.smartcloset.dto.OutfitDto;
import com.group.smartcloset.dto.SchedulerDto;
import com.group.smartcloset.model.Brand;
import com.group.smartcloset.model.Outfit;
import com.group.smartcloset.model.Scheduler;

@RestController
public class SchedulerController {

	@Autowired
	private SchedulerDao schedulerDao;


	@GetMapping("/scheduler/get-all/{idCloset}")
	public List<Scheduler> getAllSchedulers(@PathVariable Long idCloset) {
		return schedulerDao.getAllSchedulers(idCloset);
	}
	
	@PostMapping("/scheduler/save")
	public Scheduler save(@RequestBody SchedulerDto schedulerDto) {
		return schedulerDao.save(schedulerDto);
	}
	
	@GetMapping("/scheduler/get-count/{idCloset}")
	public Long getCountScheduler(@PathVariable Long idCloset) {
		return schedulerDao.getCountScheduler(idCloset);
	}
	
	 

}
