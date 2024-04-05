package com.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.constants.AppConstants;
import com.app.payloads.ApiResponse;
import com.app.payloads.PostDTO;
import com.app.payloads.PostResponse;
import com.app.service.IFileService;
import com.app.service.IPostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private IPostService postService;
	
	@Autowired
	private IFileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping(value = "/user/{userID}/category/{categoryID}/posts", consumes = "application/json")
	public ResponseEntity<PostDTO> createPosts(
			@Valid @RequestBody PostDTO postDto,
			@PathVariable(name = "userID") Long userID, 
			@PathVariable(name = "categoryID") Long categoryID) {
		
		PostDTO postDTO = postService.createPost(postDto, userID, categoryID);
		
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/user/{uID}/posts", produces = "application/json")
	public ResponseEntity<List<PostDTO>> getAllPostByUser(@PathVariable(name = "uID") Long uID){
		List<PostDTO> allPostByUser = postService.getAllPostByUser(uID);
		
		return new ResponseEntity<List<PostDTO>>(allPostByUser, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/category/{cID}/posts", produces = "application/json")
	public ResponseEntity<List<PostDTO>> getAllPostByCategory(@PathVariable(name = "cID") Long categoryID){
		List<PostDTO> allPostByCategory = postService.getAllPostByCategory(categoryID);
		
		return new ResponseEntity<List<PostDTO>>(allPostByCategory, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/posts", produces = "application/json")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize, 
			@RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDir){
		
		PostResponse allPosts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/posts/{pID}", produces = "application/json")
	public ResponseEntity<PostDTO> getPostByID(@PathVariable(name = "pID") Long pID){
		PostDTO postByID = postService.getPostByID(pID);
		return new ResponseEntity<PostDTO>(postByID, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/posts/{pID}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "pID") Long pID){
		postService.deletePost(pID);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Sucessfully !!", true) ,HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/posts/{pID}", consumes = "application/json")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable(name = "pID") Long pID){
		PostDTO updatedPost = postService.updatePost(postDto, pID);
		return new ResponseEntity<PostDTO>(updatedPost, HttpStatus.OK);
	}
	
	@GetMapping(value = "/posts/search/{keywords}", produces = "application/json")
	public ResponseEntity<List<PostDTO>> getPostsByTitle(@PathVariable(name = "keywords") String keywords){
		List<PostDTO> searchedPosts = postService.searchPost(keywords);
		return new ResponseEntity<List<PostDTO>>(searchedPosts, HttpStatus.OK);
	}
	
	
	// Post Image Upload
	@PostMapping(value = "/post/image/upload/{postID}")
	public ResponseEntity<PostDTO>  uploadPostImage(@RequestParam(name = "image") MultipartFile image,  
														  @PathVariable(name = "postID") Long postID) throws IOException{
		PostDTO postDto = postService.getPostByID(postID);
		String imageName = fileService.uploadImage(path, image);
		postDto.setImageName(imageName);
		
		PostDTO updatePost = postService.updatePost(postDto, postID);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}
	
	
	// Method to Serve File
	@GetMapping(value = "/post/image/{imageName}")
	public void downloadImage(@PathVariable(name = "imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = fileService.getResourse(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
