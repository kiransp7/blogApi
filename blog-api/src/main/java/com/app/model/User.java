package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
	
	

}
