package com.app.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.User;
import com.app.payloads.UserDTO;

@Component
public class UserModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	// DTO to Entity
	public User userDtoToUser(UserDTO userDto) {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

	// Entity to DTOx`
	public UserDTO userToUserDto(User user) {
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}
}
