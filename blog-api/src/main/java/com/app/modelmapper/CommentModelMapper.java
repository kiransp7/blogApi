package com.app.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Comment;
import com.app.payloads.CommentDTO;

@Component
public class CommentModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Comment To CommentDTO
	public CommentDTO commentToCommentDto(Comment comment) {
		
		return modelMapper.map(comment, CommentDTO.class);
	}
	
	
	// CommentDTO to Comment
	public Comment commentDtoToComment(CommentDTO commentDto) {
		
		return modelMapper.map(commentDto, Comment.class);
	}
	
	

}
