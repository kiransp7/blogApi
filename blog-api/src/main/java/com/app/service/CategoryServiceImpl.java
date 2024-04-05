package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Category;
import com.app.modelmapper.CategoryModelMapper;
import com.app.payloads.CategoryDTO;
import com.app.repository.CategoryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private CategoryModelMapper categoryModelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDto) {
		Category category = categoryModelMapper.categoryDtoTOCategory(categoryDto);
		Category saveCategory = categoryRepo.save(category);
		return categoryModelMapper.categoryTOCategoryDto(saveCategory);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDto, Long cID) {
		Category category = categoryRepo.findById(cID).orElseThrow(() -> new ResourceNotFoundException("Category", " ID ", cID));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return categoryModelMapper.categoryTOCategoryDto(category);
	}

	@Override
	public void deleteCategory(Long cID) {
		Category category = categoryRepo.findById(cID).orElseThrow(() -> new ResourceNotFoundException("Category", " ID ", cID));
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDTO getOneCategory(Long cID) {
		Category category = categoryRepo.findById(cID).orElseThrow(()-> new ResourceNotFoundException("Category", " ID ", cID));
		return categoryModelMapper.categoryTOCategoryDto(category);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categoryList = categoryRepo.findAll();
		List<CategoryDTO> categoryDtoList = categoryList
				.stream()
				.map(category -> categoryModelMapper
				.categoryTOCategoryDto(category))
				.collect(Collectors.toList());
		return categoryDtoList;
	}

}
