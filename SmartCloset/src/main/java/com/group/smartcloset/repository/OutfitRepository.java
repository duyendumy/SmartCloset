package com.group.smartcloset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.smartcloset.model.Closet;
import com.group.smartcloset.model.Item;
import com.group.smartcloset.model.Outfit;

import jakarta.transaction.Transactional;
@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Long >{
	
	@Query("SELECT o FROM Outfit o WHERE o.closet =:closet")
	public List<Outfit> getAllOutfitsOfCloset(@Param("closet") Closet closet);
	
	@Query("SELECT o FROM Outfit o WHERE o.display =:display AND o.closet =:closet")
	public List<Outfit> getAllActivedOufit(@Param("display") int display, @Param("closet") Closet closet); 
	
	@Modifying
	@Transactional
	@Query("UPDATE Outfit o SET o.display =:display WHERE o.id =:id AND o.closet =:closet")
	void deleteOutfitById(@Param("display") int display, @Param("closet") Closet closet, @Param("id") Long id);
	
	@Query("SELECT o FROM Outfit o WHERE o.id =:id") 
    public Outfit getOutfitById(@Param("id") Long id);
	
	@Query("SELECT COUNT(o) FROM Outfit o WHERE o.closet =:closet")
	public Long getCountOutfit(@Param("closet") Closet closet);
}
