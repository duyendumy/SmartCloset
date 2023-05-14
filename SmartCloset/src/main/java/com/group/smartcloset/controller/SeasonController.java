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

import com.group.smartcloset.dao.SeasonDao;
import com.group.smartcloset.model.Season;

@RestController
public class SeasonController {

	@Autowired
	private SeasonDao seasonDao;


	@GetMapping("/season/get-all")
	public List<Season> getAllBrands() {
		return seasonDao.getAllSeasons();
	}
	
	
	  



}
