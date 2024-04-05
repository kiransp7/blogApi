package com.app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDTO {
	
	private Long id;
	
	private String title;

	private String content;
	
	private Date addDate;
	
	private String imageName;

	private CategoryDTO category;
	
	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
