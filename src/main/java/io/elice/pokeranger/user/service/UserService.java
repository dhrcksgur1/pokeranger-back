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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        // Perform validation or other business logic if needed
        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }

    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(userMapper::userToUserDTO).orElse(null);
    }

    // Other CRUD methods...

}