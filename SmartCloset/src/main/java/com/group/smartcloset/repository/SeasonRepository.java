package com.group.smartcloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.smartcloset.model.Season;
@Repository
public interface SeasonRepository extends JpaRepository<Season, Long > {
	@Query("SELECT s FROM Season s WHERE s.id =:id")
	public Season getSeasonById(@Param("id") Long id);

}
