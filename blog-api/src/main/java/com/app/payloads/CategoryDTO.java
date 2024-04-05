package com.app.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CategoryDTO {
	
	private Long id;

	@JsonProperty(value = "title")
	@NotEmpty
	@Size(min = 4, message = "Title Requried !! with minumum size of Title is 4 !!")
	private String categoryTitle;

	@NotEmpty
	@JsonProperty(value = "description")
	@Size(min = 5,max = 5000 ,message = "Description Requried !! with minumum size of Description 10 !!")
	private String categoryDescription;

}
