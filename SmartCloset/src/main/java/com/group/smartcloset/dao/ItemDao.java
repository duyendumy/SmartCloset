package com.group.smartcloset.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.smartcloset.model.Item;
import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.SubCategory;
import com.group.smartcloset.model.Season;
import com.group.smartcloset.model.Closet;
import com.group.smartcloset.model.Brand;
import com.group.smartcloset.repository.ItemRepository;
import com.group.smartcloset.repository.CategoryRepository;
import com.group.smartcloset.repository.SubCategoryRepository;
import com.group.smartcloset.repository.BrandRepository;
import com.group.smartcloset.repository.SeasonRepository;
import com.group.smartcloset.repository.ClosetRepository;
import com.group.smartcloset.dto.ItemDto;


@Service
public class ItemDao {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private ClosetRepository closetRepository;

	public ItemDao(ItemRepository itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}
	
	public Item save(ItemDto itemDto) {
		Category category = categoryRepository.getCategoryById(itemDto.getCategoryId());
		SubCategory subCategory = subCategoryRepository.getSubCategoryById(itemDto.getSubCategoryId());
		Season season = seasonRepository.getSeasonById(itemDto.getSeasonId());
		List<Brand> brandList = brandRepository.getBrandByName(itemDto.getBrandName());
		Brand  brand;
		if (brandList.isEmpty()) {
			brand = brandRepository.save(new Brand(itemDto.getBrandName()));}
		else {
			brand = brandList.get(0);}
		Closet closet = closetRepository.getClosetById(itemDto.getClosetId());
		Item item = new Item(itemDto.getImagePath(), category, subCategory, brand,
				season, closet,itemDto.getPrice(),itemDto.getColor(), itemDto.getDescription(),itemDto.getDisplay());
		return itemRepository.save(item);
	}
	
	public Item updateItem(Long id, ItemDto updatedItem) {
		 Item item = itemRepository.getItemById(id);
		 
		 item.setImagePath(updatedItem.getImagePath());
		 item.setColor(updatedItem.getColor());
		 item.setDescription(updatedItem.getDescription());
		 item.setPrice(updatedItem.getPrice());
		
		 Category category = categoryRepository.getCategoryById(updatedItem.getCategoryId());
		 item.setCategory(category);
		
		 SubCategory subCategory = subCategoryRepository.getSubCategoryById(updatedItem.getSubCategoryId());
		 item.setSubCategory(subCategory);
		 
		 List<Brand> brandList = brandRepository.getBrandByName(updatedItem.getBrandName());
		 Brand  brand;
		 if (brandList.isEmpty()) {
			brand = brandRepository.save(new Brand(updatedItem.getBrandName()));}
		 else {
			brand = brandList.get(0);}
		 item.setBrand(brand);
		 
		 Season season = seasonRepository.getSeasonById(updatedItem.getSeasonId());
		 item.setSeason(season);
		 	
		return itemRepository.save(item);
	}
	
	 public List<Item> getAllItems(Long closetId) {
	//	return itemRepository.findAll();
		 Closet closet = closetRepository.getClosetById(closetId);
		 return itemRepository.getAllActivedItem(1,closet);
	}
	
	public Item getItemById(Long id) {
		return itemRepository.getItemById(id);
	}
	
	@Transactional
	public void deleteItemById(Long id) {
		 this.itemRepository.deleteItem(0,id);
	}
	public List<Item> getItemsOfCategory(Long idCloset, Long idCategory){
		Closet closet = closetRepository.getClosetById(idCloset);
		Category category = categoryRepository.getCategoryById(idCategory);
		return itemRepository.getItemsOfCategory(closet,category,1);
	}
	
	 public Long getCountItem(Long idCloset) {
		 Closet closet = closetRepository.getClosetById(idCloset);
		 return itemRepository.getCountItem(closet);
	 }

}
