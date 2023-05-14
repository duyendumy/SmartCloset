package com.group.smartcloset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.Closet;
import com.group.smartcloset.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long >{
	
	@Query("SELECT i FROM Item i WHERE i.id =:id")
	public Item getItemById(@Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Item i SET i.display = :display WHERE i.id LIKE :id")
	void deleteItem(@Param("display") int display,  @Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Item i SET i.display = :display WHERE i.id LIKE :id")
	void updateItem(@Param("display") int display,  @Param("id") Long id);
	
	@Query("SELECT i FROM Item i WHERE i.closet =:closet AND i.category =:category AND i.display =:display")
	public List<Item> getItemsOfCategory(@Param("closet") Closet closet, @Param("category") Category category, @Param("display") int display);
	
	@Query("SELECT i FROM Item i WHERE i.display =:display AND i.closet =:closet")
	public List<Item> getAllActivedItem(@Param("display") int display, @Param("closet") Closet closet); 
	

	@Query("SELECT COUNT(i) FROM Item i WHERE i.closet =:closet")
	public Long getCountItem(@Param("closet") Closet closet);

}
