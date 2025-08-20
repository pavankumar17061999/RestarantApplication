package com.pavan.userInfo.service;

 // ✅ Fixed package name
import com.pavan.userInfo.Mapper.UserMapper;
import com.pavan.userInfo.dto.UserDTO;
import com.pavan.userInfo.enitiy.User;         // ✅ Fixed package name
import com.pavan.userInfo.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper; // ✅ Injected instead of static INSTANCE

    // ✅ Constructor injection (preferred for testability)
    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public UserDTO addUserInDB(UserDTO userDTO) {
        User entity = userMapper.mapUserDTOToUser(userDTO);  // No INSTANCE
        User savedUser = userRepo.save(entity);
        return userMapper.mapUserToUserDTO(savedUser);
    }

    public ResponseEntity<UserDTO> findByUserID(Integer userId) {
        Optional<User> fetchedUser = userRepo.findById(userId);
        return fetchedUser
                .map(user -> new ResponseEntity<>(userMapper.mapUserToUserDTO(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
