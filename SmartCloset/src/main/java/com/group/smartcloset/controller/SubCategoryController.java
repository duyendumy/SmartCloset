package com.group.smartcloset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group.smartcloset.dao.CategoryDao;
import com.group.smartcloset.dao.SubCategoryDao;
import com.group.smartcloset.dto.CategoryDto;
import com.group.smartcloset.dto.SubCategoryDto;
import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.SubCategory;

@RestController
public class SubCategoryController {
	
	@Autowired
	private SubCategoryDao subCategoryDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	@GetMapping("/subcategory/get-all")
	public List<SubCategory> getAllSubCategorys() {
		return subCategoryDao.getAllSubCategorys();
	}
	
	@GetMapping("/subcategory/{idCategory}")
	public List<SubCategory> getSubCategoriesOfCategory(@PathVariable Long idCategory) {
		Category selectedCategory = this.categoryDao.getCategoryById(idCategory);
		List<SubCategory> subCategoryList =  this.subCategoryDao.getSubCategoriesOfCategory(selectedCategory);
		return subCategoryList;
	}
	
	@PostMapping("/subcategory/save")
	public SubCategory save(@RequestBody SubCategoryDto subCategoryDto) {
		return subCategoryDao.save(subCategoryDto);
	}


}
