package com.app.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Category;
import com.app.payloads.CategoryDTO;

@Component
public class CategoryModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public Category categoryDtoTOCategory(CategoryDTO categoryDto) {
		
		return modelMapper.map(categoryDto, Category.class);
	}

	
	public CategoryDTO categoryTOCategoryDto(Category category) {
		
		return modelMapper.map(category, CategoryDTO.class);
	}
	
	
	
	

}
