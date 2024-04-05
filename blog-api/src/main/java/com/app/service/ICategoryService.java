package com.app.service;

import java.util.List;

import com.app.payloads.CategoryDTO;

public interface ICategoryService {
	
	// In Interface by Default all Methods are Public and Abstract
	
	 CategoryDTO createCategory(CategoryDTO categoryDto);
	
	 CategoryDTO updateCategory(CategoryDTO categoryDto, Long cID);
	
	 void deleteCategory(Long cID);
	
	 CategoryDTO getOneCategory(Long cID);
	
	 List<CategoryDTO> getAllCategory();
	

}
