package com.pavan.userInfo.Mapper; // also fix package name: lowercase + correct spelling

import com.pavan.userInfo.dto.UserDTO;
import com.pavan.userInfo.enitiy.User; // also fix "enitiy" to "entity"
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapUserDTOToUser(UserDTO userDTO);
    UserDTO mapUserToUserDTO(User user);
}
