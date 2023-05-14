package com.group.smartcloset.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.smartcloset.model.Closet;
import com.group.smartcloset.model.Outfit;
import com.group.smartcloset.model.Scheduler;


@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Long > {

	
	@Query("SELECT s FROM Scheduler s WHERE s.closet =:closet")
	public List<Scheduler> getAllSchedulers(@Param("closet") Closet closet);
	
	@Query("SELECT COUNT(s) FROM Scheduler s WHERE s.closet =:closet")
	public Long getCountScheduler(@Param("closet") Closet closet);
}
