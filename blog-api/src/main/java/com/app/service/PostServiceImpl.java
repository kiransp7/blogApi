package com.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Category;
import com.app.model.Post;
import com.app.model.User;
import com.app.modelmapper.PostModelMapper;
import com.app.payloads.PostDTO;
import com.app.payloads.PostResponse;
import com.app.repository.CategoryRepo;
import com.app.repository.PostRepo;
import com.app.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements IPostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private PostModelMapper postModelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	
	@CacheEvict(cacheNames =  {"AllPostByCategory", "AllPostByUser", "AllPosts"}, allEntries = true)
	@Override
	public PostDTO createPost(PostDTO postDto, Long userID, Long categoryID) {

		User user = userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", " User ID ", userID));

		Category category = categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " Category ID ", categoryID));

		Post post = postModelMapper.postDtoToPost(postDto);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post newPost = postRepo.save(post);
		return postModelMapper.postToPostDto(newPost);
	}

	@CacheEvict(cacheNames =  {"AllPostByCategory", "AllPostByUser", "AllPosts"}, allEntries = true)
	@Override
	public PostDTO updatePost(PostDTO postDto, Long pID) {

		Post post = postRepo.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Post", " ID ", pID));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post postSaved = postRepo.save(post);
		
		return postModelMapper.postToPostDto(postSaved);
	}

	@Cacheable("AllPostByCategory")
	@Override
	public List<PostDTO> getAllPostByCategory(Long categoryID) {
		Category category = categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " ID ", categoryID));
		List<Post> list = postRepo.findByCategory(category);

		// Way : 1 -> Post List Post Convert PostDTO List (Using Stream API)
		List<PostDTO> allPostsByCategorys = list.stream().map(p -> postModelMapper.postToPostDto(p))
				.collect(Collectors.toList());
		
		return allPostsByCategorys;
	}

	@Cacheable("AllPostByUser")
	@Override
	public List<PostDTO> getAllPostByUser(Long userID) {

		User user = userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", " ID ", userID));
		List<Post> postList = postRepo.findByUser(user);
		List<PostDTO> postAL = new ArrayList<>();

		// Way : 2 -> Post List Post Convert PostDTO List (Using Collections)
		for (Post p : postList) {
			postAL.add(postModelMapper.postToPostDto(p));
		}

		return postAL;
	}

	@Cacheable("AllPosts")
	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc") 
				? Sort.by(sortBy).ascending() 
						: Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = postRepo.findAll(pageable);
		
		List<Post> allPostsByPage = pagePost.getContent();
		
		List<PostDTO> allPostDTOs = allPostsByPage.stream().map(p -> postModelMapper.postToPostDto(p)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(allPostDTOs);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastpage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDTO getPostByID(Long pID) {
		Post post = postRepo.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Post", " ID ", pID));
		return postModelMapper.postToPostDto(post);
	}

	
	@Override
	public void deletePost(Long pID) {
		Post post = postRepo.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Post", " ID ", pID));
		postRepo.delete(post);
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
	
		List<Post> allPost = postRepo.findByTitleContaining(keyword);
		List<PostDTO> allPostDtos = allPost.stream().map(p -> postModelMapper.postToPostDto(p)).collect(Collectors.toList());
		return allPostDtos;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
