package com.group.smartcloset;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.group.smartcloset.dao.UserDao;
import com.group.smartcloset.dao.BrandDao;
import com.group.smartcloset.dao.CategoryDao;
import com.group.smartcloset.dao.ClosetDao;
import com.group.smartcloset.dao.SubCategoryDao;
import com.group.smartcloset.dao.SeasonDao;
import com.group.smartcloset.dao.ItemDao;
import com.group.smartcloset.dao.OutfitDao;
import com.group.smartcloset.dto.UserRegistrationDto;
import com.group.smartcloset.dto.CategoryDto;
import com.group.smartcloset.dto.SubCategoryDto;
import com.group.smartcloset.dto.ItemDto;
import com.group.smartcloset.model.User;
import com.group.smartcloset.model.Category;
import com.group.smartcloset.model.Item;
import com.group.smartcloset.model.Outfit;
import com.group.smartcloset.model.SubCategory;

@SpringBootTest
class SmartClosetApplicationTests {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private SubCategoryDao subCategoryDao;
	
	@Autowired
	private ClosetDao closetDao;
	
	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private SeasonDao seasonDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private OutfitDao outfitDao;
	
	
	
	// @Test
	void saveUser() {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		userRegistrationDto.setEmail("kenny@gmail.com");
		userRegistrationDto.setPassword("1234");
		User user = userDao.save(userRegistrationDto );
		System.out.println(user);
	}
	

	// @Test
	void getAllUsers() {
		List<User> users = userDao.getAllUsers();
		System.out.println(users);
	}
	
	// @Test
	void getUser() {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		userRegistrationDto.setEmail("chris@gmail.com");
		userRegistrationDto.setPassword("1234");
		User user = userDao.getUser(userRegistrationDto );
		System.out.println(user);
	}
	
	//@Test
	void addCategoryTest() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName("dress");
		categoryDao.save(categoryDto);
		
	}
	
	// @Test
	void addSubCategoryTest() {
		Long categoryId = Long.valueOf(1);
		SubCategoryDto subcategoryDto = new SubCategoryDto();
		subcategoryDto.setName("short");
		subcategoryDto.setCategoryId(categoryId);
		subCategoryDao.save(subcategoryDto);
		
	}
	
	// @Test
	void getAllCategorys() {
		List<Category> categorys = categoryDao.getAllCategorys();
		System.out.println(categorys);
	}
	
	// @Test
	void deleteCategory() {
		Long id = (long) 1;
		this.categoryDao.deleteCategory(id);
	}
	
	//@Test
	void addBrand() {
		this.brandDao.save("FOREVER21");
		
	}
	
	//@Test
	void addSeason() {
		this.seasonDao.save("Winter");
		
	}
	
   // @Test
	void addItem() {
		Long id = (long) 3;
		Float price = (float)100;
		ItemDto itemDto = new ItemDto("ccc",(long)3,(long)2,"FOREVER 21",id,id,price,"aaa","red",1);
		this.itemDao.save(itemDto);
		
	}
	
	// @Test
	void getAllItems() {
		List<Item> items = itemDao.getAllItems((long)1);
		if( items.isEmpty())
		{
			System.out.println("Not things");
		}
		System.out.println("Have it");
		System.out.println(items);

		
	}
	
	// @Test
	void getAllSubCategory() {
		List<SubCategory> subcategorys = subCategoryDao.getAllSubCategorys();
		if( subcategorys.isEmpty())
		{
			System.out.println("Not things");
		}
		System.out.println("Have it");
		System.out.println(subcategorys);
		
	}
	
	//@Test
	void getItemsOfCategory() {
		//Category category = categoryDao.getCategoryById((long)3);
		//List<Item> itemList = itemDao.getItemsOfCategory(category);
		//System.out.println(itemList);
	}
	
	//@Test
	void getOutfits() {
		List<Outfit> listOutfit = outfitDao.getAllOutfits((long)1);
		System.out.println(listOutfit);
	}
	
	
		
	
	
	
	
	
	



}
