package com.sutisoft.contentmanagement.services;

import java.util.List;

import com.sutisoft.contentmanagement.command.CategoryCommand;
import com.sutisoft.contentmanagement.domain.Category;


public interface CategoryService {

	public Category findById(Integer categoryId);
	
	public List<Category> findAll();
	
	public Category save(Category category, String apiKey);

	public Integer update(CategoryCommand categoryCommand, String apiKey);
	
	public Category saveOrUpdate(CategoryCommand categoryCommand, String apiKey);
	
	public Integer delete(Category category,String apiKey);
}
