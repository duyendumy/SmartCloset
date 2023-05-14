package com.group.smartcloset.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.group.smartcloset.model.Brand;
import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.SubCategory;
import com.group.smartcloset.repository.BrandRepository;

@Service
public class BrandDao {

	@Autowired
	private BrandRepository brandRepository;

	public BrandDao(BrandRepository brandRepository) {
		super();
		this.brandRepository = brandRepository;
	}

	
	  public Brand save(String brandName) { 
	  Brand brand = new Brand(brandName);
	  return brandRepository.save(brand); }
	 

	public Brand getBrandById(Long id) {
		return brandRepository.getBrandById(id);
	}
	
	public List<Brand> getAllBrands() {
		return brandRepository.findAll();
	}
	
	public List<Brand> getBrandByName(String name){
		return brandRepository.getBrandByName(name);
	}

}
