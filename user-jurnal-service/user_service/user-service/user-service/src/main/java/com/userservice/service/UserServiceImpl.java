package com.userservice.service;

import com.userservice.dto.UserDTO;
import com.userservice.entity.User;
import com.userservice.exception.UserNotFoundException;
import com.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }
    @Override
    public List<UserDTO> getAllUsers() {
        List<com.userservice.entity.User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTO::convertFromEntity) // Method reference
                .collect(Collectors.toList());
    }




    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return convertToDTO(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Update user attributes
            user.setUsername(userDTO.getUsername());
            // Update other attributes
            User updatedUser = userRepository.save(user);
            return convertToDTO(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<com.userservice.entity.User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    // Helper method for conversion from entity to DTO using ModelMapper
    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}

