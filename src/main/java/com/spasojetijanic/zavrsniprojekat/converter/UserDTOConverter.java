package com.spasojetijanic.zavrsniprojekat.converter;

import com.spasojetijanic.zavrsniprojekat.dto.UserDTO;
import com.spasojetijanic.zavrsniprojekat.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {
  @Autowired
  private ModelMapper modelMapper;

  public UserDTO convertToDto(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  public User convertToEntity(UserDTO userDTO) {
    return modelMapper.map(userDTO, User.class);
  }
}
