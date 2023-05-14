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

import com.group.smartcloset.dao.BrandDao;
import com.group.smartcloset.model.Brand;

@RestController
public class BrandController {

	@Autowired
	private BrandDao brandDao;


	@GetMapping("/brand/get-all")
	public List<Brand> getAllBrands() {
		return brandDao.getAllBrands();
	}
	
	
	  



}
