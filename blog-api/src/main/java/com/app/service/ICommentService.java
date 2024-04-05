package com.app.service;

import com.app.payloads.CommentDTO;

public interface ICommentService {
	
	CommentDTO createComment(CommentDTO commentDto, Long postID);
	
	void deleteComment(Long commentID);

}
