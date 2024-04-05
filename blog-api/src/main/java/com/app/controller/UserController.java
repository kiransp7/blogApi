package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.ApiResponse;
import com.app.payloads.UserDTO;
import com.app.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
		return new ResponseEntity<UserDTO>(userService.createUser(userDto), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{userID}", consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userdto, @PathVariable(name = "userID") Long Id){
		UserDTO updateUser = userService.updateUser(userdto, Id);
		return new ResponseEntity<UserDTO>(updateUser, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{UserID}", produces = "application/json")
	public ResponseEntity<UserDTO> getUserByID(@PathVariable(name = "UserID") Long Id){
		return new ResponseEntity<UserDTO>(userService.getUserById(Id), HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return new ResponseEntity<List<UserDTO>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	// consumes = "application/json" not applicable on DeleteMapping
	
	@DeleteMapping(value = "/{userID}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable(name = "userID") Long uId){
		userService.deleteUser(uId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Sucessfully..!! ", true),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	

}
