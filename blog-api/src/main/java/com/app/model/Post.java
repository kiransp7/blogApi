package com.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
	
	
	private String title;
	
	private String content;
	
	@NotEmpty(message = "Image Requried !!")
	@JsonProperty(value = "image")
	@Column(name = "image")
	private String imageName;

	@Temporal(TemporalType.DATE)
	@Column(name = "post_date")
	private Date addDate;
	
	
	@ManyToOne
	private Category category;
	
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
	
	
	

}
