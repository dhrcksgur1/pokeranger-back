package io.elice.pokeranger.user.service;

import io.elice.pokeranger.user.mapper.UserMapper;
import io.elice.pokeranger.user.entity.User;
import io.elice.pokeranger.user.entity.UserDTO;
import io.elice.pokeranger.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);

        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }

    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(userMapper::userToUserDTO).orElse(null);
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(user -> {
            // Update user fields with values from userDTO
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setAddress(userDTO.getAddress());
            user.setType(userDTO.getType());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setPasswordHash(userDTO.getPasswordHash());

            userRepository.save(user);
            return userMapper.userToUserDTO(user);
        }).orElse(null);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}