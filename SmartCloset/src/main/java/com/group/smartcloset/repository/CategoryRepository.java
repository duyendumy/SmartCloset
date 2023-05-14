package com.group.smartcloset.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.smartcloset.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long > {
	
	@Modifying
	@Query("UPDATE Category c SET c.display = :display WHERE c.id LIKE :id")
	void deleteCategory(@Param("display") int display,  @Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Category c SET c.display = :display WHERE c.id LIKE :id")
	void updateCategory(@Param("display") int display,  @Param("id") Long id);

	@Query("SELECT c FROM Category c WHERE c.id =:id")
	public Category getCategoryById(@Param("id") Long id);
	
	@Query("SELECT c FROM Category c WHERE c.display =:display")
	public List<Category> getAllActivedCategory(@Param("display") int display); 

}
