package com.group.smartcloset.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.smartcloset.dto.OutfitDto;
import com.group.smartcloset.model.Closet;
import com.group.smartcloset.model.Item;
import com.group.smartcloset.model.Outfit;
import com.group.smartcloset.repository.ClosetRepository;
import com.group.smartcloset.repository.ItemRepository;
import com.group.smartcloset.repository.OutfitRepository;

@Service
public class OutfitDao {
	
	@Autowired
	private OutfitRepository outfitRepository;
	
	@Autowired
	private ClosetRepository closetRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	public OutfitDao(OutfitRepository outfitRepository) {
		super();
		this.outfitRepository = outfitRepository;
	}
	
	public Outfit save(OutfitDto outfitDto) {
		Outfit outfit = new Outfit();
		Closet closet = closetRepository.getClosetById(outfitDto.getClosetId());
		outfit.setCloset(closet);
		outfit.setDisplay(outfitDto.getDisplay());
		List<Long> itemIdList =  outfitDto.getItemIdList();	
		Collection<Item> itemList = new ArrayList<Item>();
		for(int i = 0; i < itemIdList.size(); i++) {
			Item item = itemRepository.getItemById(itemIdList.get(i));
			itemList.add(item);
		}
		outfit.setItems(itemList);
		return outfitRepository.save(outfit);
	}
	
	 public List<Outfit> getAllOutfits(Long closetId) {
			//return outfitRepository.findAll();
		 Closet closet = closetRepository.getClosetById(closetId);
		 return outfitRepository.getAllActivedOufit(1,closet);
	}
	 public List<Outfit> getAllOutfitsOfCloset(Long closetId) {
		 Closet closet = closetRepository.getClosetById(closetId);
		 return outfitRepository.getAllOutfitsOfCloset(closet);
	}
	 
	 public void deleteOutfitById(Long idCloset,Long idOutfit ) {
		 Closet closet = closetRepository.getClosetById(idCloset);
		 this.outfitRepository.deleteOutfitById(0,closet,idOutfit);
	 }
	 
	 public Long getCountOutfit(Long idCloset) {
		 Closet closet = closetRepository.getClosetById(idCloset);
		 return outfitRepository.getCountOutfit(closet);
	 }


}
