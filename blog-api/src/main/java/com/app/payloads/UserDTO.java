package com.app.payloads;

import jakarta.validation.constraints.Email;
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
public class UserDTO {
	
	private Long id;
	
	@NotEmpty(message = "User Name Must be Requried !!")
	private String name;
	
	@Email(message = "Please Enter Valid Email !!")
	private String email;
	
	@NotEmpty(message = "Password Must be 4 to 10  !!")
	@Size(min = 4, max = 10)
	private String password;

	@NotEmpty(message = "About Data Requried !!")
	@Size(max = 1000)
	private String about;

	
}
