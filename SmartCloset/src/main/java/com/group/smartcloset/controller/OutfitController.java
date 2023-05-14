package com.group.smartcloset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group.smartcloset.dao.OutfitDao;
import com.group.smartcloset.dto.OutfitDto;
import com.group.smartcloset.model.Outfit;

@RestController
public class OutfitController {
	@Autowired
	private OutfitDao outfitDao;
	
	@GetMapping("/outfit/get-all/{idCloset}")
	public List<Outfit> getAllOutfits(@PathVariable Long idCloset) {
		return outfitDao.getAllOutfits(idCloset);
	}
	
	@GetMapping("/outfit/get-count/{idCloset}")
	public Long getCountOutfit(@PathVariable Long idCloset) {
		return outfitDao.getCountOutfit(idCloset);
	}
	
	@PostMapping("/outfit/save")
	public Outfit save(@RequestBody OutfitDto oufitDto) {
		return outfitDao.save(oufitDto);
	}
	
	@GetMapping("/outfit/delete/{idCloset}/{idOutfit}")
	public void deleteItemById(@PathVariable Long idCloset,@PathVariable Long idOutfit) {
		 this.outfitDao.deleteOutfitById(idCloset,idOutfit );
	}

}
