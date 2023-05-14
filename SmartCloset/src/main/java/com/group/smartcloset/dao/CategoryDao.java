package com.group.smartcloset.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.smartcloset.model.Category;
import com.group.smartcloset.repository.CategoryRepository;
import com.group.smartcloset.dto.CategoryDto;

@Service
public class CategoryDao {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryDao(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	public Category save(CategoryDto categoryDto) {
		Date currentDate = new Date();
		Category category = new Category(categoryDto.getImagePath(),categoryDto.getName(), currentDate,currentDate,1);
		return categoryRepository.save(category);
	}
	
	public Category save1(Category category) {
		return categoryRepository.save(category);
	}
		
	public List<Category> getAllCategorys() {
		// return categoryRepository.findAll();
		return categoryRepository.getAllActivedCategory(1);
	}
	
	public Category getCategoryById(Long id) {
		return categoryRepository.getCategoryById(id);
	}
	
	@Transactional
	public void deleteCategory(Long id) {
		 this.categoryRepository.deleteCategory(0,id);
	}
	
	@Transactional
	public void updateCategory(Long id) {
		this.categoryRepository.updateCategory(0,id);
	}

}
