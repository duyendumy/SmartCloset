package com.group.smartcloset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long > {
	
	@Query("SELECT c FROM SubCategory c WHERE c.id =:id")
	public SubCategory getSubCategoryById(@Param("id") Long id);
	
	@Query("SELECT c FROM SubCategory c WHERE c.category =:category")
	public List<SubCategory> getSubCategoriesOfCategory(@Param("category") Category category);

}
