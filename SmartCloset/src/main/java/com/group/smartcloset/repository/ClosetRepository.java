package com.group.smartcloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.smartcloset.model.Closet;

@Repository
public interface ClosetRepository  extends JpaRepository<Closet, Long >{
	
	
	  @Query("SELECT c FROM Closet c WHERE c.id =:id") 
	  public Closet getClosetById(@Param("id") Long id);
	 

}
