package com.userservice.service;

import com.userservice.dto.UserDTO;
import com.userservice.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id) throws UserNotFoundException;

    UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException;

    void deleteUser(Long id) throws UserNotFoundException;
}

