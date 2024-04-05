package com.app.service;

import java.util.List;

import com.app.payloads.PostDTO;
import com.app.payloads.PostResponse;

public interface IPostService {

	PostDTO createPost(PostDTO postDto, Long userID, Long categoryID); // Done

	PostDTO updatePost(PostDTO postDto, Long pID); //Done

	List<PostDTO> getAllPostByCategory(Long categoryID); // Done

	List<PostDTO> getAllPostByUser(Long userID); // Done
	
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir); // Done

	PostDTO getPostByID(Long pID); // Done

	void deletePost(Long pID); // Done

	List<PostDTO> searchPost(String keyword); 
	
	
	
	
	
	

}
