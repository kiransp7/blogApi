package com.app.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Post;
import com.app.payloads.PostDTO;

@Component
public class PostModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PostDTO postToPostDto(Post post) {
		return modelMapper.map(post, PostDTO.class);
	}
	
	public Post postDtoToPost(PostDTO postDto) {
		return modelMapper.map(postDto, Post.class);
	}
	
	
	
	
	

}
