package com.group.smartcloset.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.smartcloset.dto.SubCategoryDto;
import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.SubCategory;
import com.group.smartcloset.repository.CategoryRepository;
import com.group.smartcloset.repository.SubCategoryRepository;

@Service
public class SubCategoryDao {
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public SubCategoryDao(SubCategoryRepository subCategoryRepository) {
		super();
		this.subCategoryRepository = subCategoryRepository;
	}
	
	public SubCategory save(SubCategoryDto subCategoryDto) {
		Category selectedCategory = categoryRepository.getCategoryById(subCategoryDto.getCategoryId());
		if ( selectedCategory != null)
		{
			Date currentDate = new Date();
			SubCategory subCategory = new SubCategory(selectedCategory,subCategoryDto.getName(), currentDate,currentDate,1);
			return subCategoryRepository.save(subCategory);
		}
		return null;
	}
	
	public SubCategory getSubCategoryById(Long id) {
		return subCategoryRepository.getSubCategoryById(id);
	}
	
	public List<SubCategory> getAllSubCategorys() {
		return subCategoryRepository.findAll();
	}
	
	public List<SubCategory> getSubCategoriesOfCategory(Category category) {
		return subCategoryRepository.getSubCategoriesOfCategory(category);
	}
	

}
