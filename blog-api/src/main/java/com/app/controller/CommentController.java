package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.ApiResponse;
import com.app.payloads.CommentDTO;
import com.app.service.ICommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;

	
	@PostMapping(value = "/post/{postID}/comments", consumes = "application/json")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDto, @PathVariable(name = "postID") Long postID) {
		CommentDTO createComment = commentService.createComment(commentDto, postID);
		return new ResponseEntity<CommentDTO>(createComment,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "/comments/{commentID}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "commentID") Long commentID){
		commentService.deleteComment(commentID);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted Sucessfully !!", true),HttpStatus.OK);
	}
	
	
	
	

}
