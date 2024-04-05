package com.app.service;

import java.util.*;

import com.app.payloads.UserDTO;

public interface IUserService {
	
	public UserDTO createUser(UserDTO user);
	
	public UserDTO updateUser(UserDTO user,Long uId );
	
	public UserDTO getUserById(Long uId);
	
	public List<UserDTO> getAllUsers();
	
	public void deleteUser(Long uId);
	
	
	

}
