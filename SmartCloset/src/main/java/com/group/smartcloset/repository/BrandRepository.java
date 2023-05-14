package com.group.smartcloset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.smartcloset.model.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long > {
	@Query("SELECT b FROM Brand b WHERE b.id =:id")
	public Brand getBrandById(@Param("id") Long id);
	
	@Query("SELECT b FROM Brand b WHERE b.name =:name")
	public List<Brand> getBrandByName(@Param("name") String name);
	

}
