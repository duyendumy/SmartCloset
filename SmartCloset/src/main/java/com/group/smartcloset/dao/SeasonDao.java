package com.group.smartcloset.dao;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.smartcloset.model.Brand;
import com.group.smartcloset.model.Closet;
import com.group.smartcloset.model.Season;
import com.group.smartcloset.repository.SeasonRepository;

@Service
public class SeasonDao {
	
	@Autowired
	private SeasonRepository seasonRepository;

	public SeasonDao(SeasonRepository seasonRepository) {
		super();
		this.seasonRepository = seasonRepository;
	}
	
	public Season save(String seasonName) {
		Season season = new Season(seasonName);
		return seasonRepository.save(season);
	}
	
	public Season getSeasonById(Long id) {
		return seasonRepository.getSeasonById(id);
	}
	
	public List<Season> getAllSeasons() {
		return seasonRepository.findAll();
	}

}
