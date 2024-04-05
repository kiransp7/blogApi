package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.ApiResponse;
import com.app.payloads.CategoryDTO;
import com.app.service.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {
	
	
	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<CategoryDTO> createCategoryData(@Valid @RequestBody CategoryDTO categoryDto){
		CategoryDTO createCategory = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDTO>(createCategory, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{cID}", consumes = "application/json")
	 public ResponseEntity<CategoryDTO> updateCategoryData(@Valid @RequestBody CategoryDTO categoryDto,@PathVariable(name = "cID") Long cID){
		return new ResponseEntity<CategoryDTO>(categoryService.updateCategory(categoryDto, cID),HttpStatus.CREATED);
	 }
	
	@DeleteMapping(value = "/{cID}")
	public ResponseEntity<ApiResponse> deleteCategoryByID(@PathVariable Long cID){
		categoryService.deleteCategory(cID);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Sucessfully..!!", true), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{cID}", produces = "application/json")
	public ResponseEntity<CategoryDTO> getOneCategoryData(@PathVariable(name = "cID") Long cID){
		return new ResponseEntity<CategoryDTO>(categoryService.getOneCategory(cID), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<CategoryDTO>> getAllCategoryData(){
		return new ResponseEntity<List<CategoryDTO>>(categoryService.getAllCategory(), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
