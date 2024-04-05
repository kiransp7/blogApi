package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.ResourceNotFoundException;
import com.app.helper.GmailHelperClass;
import com.app.model.User;
import com.app.modelmapper.UserModelMapper;
import com.app.payloads.UserDTO;
import com.app.repository.UserRepo;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserModelMapper userModelMapper;
	
	@Autowired
	private GmailHelperClass gmail;
	
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		
		User user = userModelMapper.userDtoToUser(userDto);
		User saveUser = userRepo.save(user);
		System.out.println("User Saved : -->> " +saveUser);
		gmail.sendEmail(userDto.getName(), userDto.getEmail(), userDto.getPassword());
		return userModelMapper.userToUserDto(saveUser);
	}
	
	@Override
	public UserDTO updateUser(UserDTO userDto, Long uid) {
		User user = userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User", " id ", uid));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User saveUser = userRepo.save(user);
		return userModelMapper.userToUserDto(saveUser);
	}
	
	@Override
	public UserDTO getUserById(Long uId) {
		User user = userRepo.findById(uId)
				.orElseThrow(()-> new ResourceNotFoundException("User", " id ", uId));
		return userModelMapper.userToUserDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDTO> userDtos = users.stream().map(userModelMapper :: userToUserDto).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Long uId) {
		User user = userRepo.findById(uId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " ID ", uId));
		userRepo.delete(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}