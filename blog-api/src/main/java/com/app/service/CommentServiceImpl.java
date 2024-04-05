package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Comment;
import com.app.model.Post;
import com.app.modelmapper.CommentModelMapper;
import com.app.payloads.CommentDTO;
import com.app.repository.CommentRepo;
import com.app.repository.PostRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentModelMapper commentModelMapper;
	
	
	@Override
	public CommentDTO createComment(CommentDTO commentDto, Long postID) {
		
		Post post = postRepo.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", " Post_ID ", postID));
		
		Comment comment = commentModelMapper.commentDtoToComment(commentDto);
		comment.setContent(commentDto.getContent());
		comment.setPost(post);
		
		Comment saveComment = commentRepo.save(comment);
		
		return commentModelMapper.commentToCommentDto(saveComment);
	}

	
	@Override
	public void deleteComment(Long commentID) {
		Comment comment = commentRepo.findById(commentID).orElseThrow(() -> new ResourceNotFoundException("Comment", " Comment_ID ", commentID));
		commentRepo.delete(comment);
	}
	
	
	
	

}
