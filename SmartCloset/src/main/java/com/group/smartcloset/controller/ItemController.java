package com.group.smartcloset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group.smartcloset.dao.CategoryDao;
import com.group.smartcloset.dao.ItemDao;
import com.group.smartcloset.dto.ItemDto;
import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.Item;


@RestController
public class ItemController {
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@PostMapping("/item/save")
	public Item save(@RequestBody ItemDto itemDto) {
		return itemDao.save(itemDto);
	}
	
	@GetMapping("/item/get-count/{idCloset}")
	public Long getCountItem(@PathVariable Long idCloset) {
		return itemDao.getCountItem(idCloset);
	}
	
	
	@GetMapping("/item/get-all/{idCloset}")
	public List<Item> getAllItems(@PathVariable Long idCloset) {
		return itemDao.getAllItems(idCloset);
	}
	
	@GetMapping("/item/{id}")
	public Item getItem(@PathVariable Long id) {
		Item item = new Item();
		item =  this.itemDao.getItemById(id);
		if (item == null)
		{
			System.out.print("Khong co");
		}
		return item;
	}
	
	  @GetMapping("/get-item-by-category/{idCloset}/{idCategory}") 
	  public List<Item> getItemsOfCategory(@PathVariable Long idCloset, @PathVariable Long idCategory) { 
	//  Category selectedCategory = this.categoryDao.getCategoryById(idCategory); 
	  return itemDao.getItemsOfCategory(idCloset, idCategory); }
	 
	
	@GetMapping("/item/delete/{id}")
	public void deleteItemById(@PathVariable Long id) {
		 this.itemDao.deleteItemById(id);
	}
	
	
	 @PutMapping("/item/update/{id}")
	 public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @RequestBody ItemDto updatedItem) { 
		this.itemDao.updateItem(id, updatedItem);
		return ResponseEntity.ok(updatedItem);
	 }
	

}
