package com.pavan.userInfo.contoller;

import com.pavan.userInfo.dto.UserDTO;
import com.pavan.userInfo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("Received request to add user: {}", userDTO.getUserName());
        UserDTO savedUser = userService.addUserInDB(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/fetchUserById/{userId}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable Integer userId) {
        log.info("Fetching user by ID: {}", userId);
        return userService.findByUserID(userId);
    }
}
