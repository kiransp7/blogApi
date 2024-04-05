package com.app.payloads;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class CommentDTO {

	private Long id;
	
	private String content;

}
