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

import com.group.smartcloset.dao.CategoryDao;
import com.group.smartcloset.dto.CategoryDto;
import com.group.smartcloset.model.Category;

@RestController
public class CategoryController {

	@Autowired
	private CategoryDao categoryDao;

	@PostMapping("/category/save")
	public Category save(@RequestBody CategoryDto categoryDto) {
		return categoryDao.save(categoryDto);
	}

	@GetMapping("/category/get-all")
	public List<Category> getAllCategorys() {
		return categoryDao.getAllCategorys();
	}
	
	@GetMapping("/category/{id}")
	public Category getCategory(@PathVariable Long id) {
		return categoryDao.getCategoryById(id);
	}

	
	 @GetMapping("/category/delete/{id}")
	 public void deleteCategory(@PathVariable Long id) { 
		 this.categoryDao.deleteCategory(id);
	  
	 }
	 
		/*
		 * public ResponseEntity<CategoryDto> createCategory (@RequestBody CategoryDto
		 * category){ return new ResponseEntity<>(category, HttpStatus.CREATED); }
		 */
	 
	 @PutMapping("/category/update/{id}")
	 public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto updatedCategory) {
		Category category = categoryDao.getCategoryById(id);
		category.setName(updatedCategory.getName());
		category.setImagePath(updatedCategory.getImagePath());
		category.setDisplay(updatedCategory.getDisplay());
		this.categoryDao.save1(category);
		return ResponseEntity.ok(updatedCategory);
	 }
	 
	 @PutMapping("/category/delete/{id}")
	 public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long id, @RequestBody CategoryDto deletedCategory) {
		Category category = categoryDao.getCategoryById(id);
		//category.setName(updatedCategory.getName());
		category.setDisplay(deletedCategory.getDisplay());
		this.categoryDao.save1(category);
		return ResponseEntity.ok(deletedCategory);
	 }
	 
	  



}
