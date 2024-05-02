package com.exam.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;

@Service
public interface CategoryService {

	//create category
	Category addCategory(Category category);
	
	//update category 
	Category updateCategory(Category category);
	
	//get all category
	Set<Category> getCategories();
	
	//get single category
	Category getCategory(Long categoryId);
	
	//delete category
	public void deleteCategory(Long categoryId);
}
