package io.elice.pokeranger.user.service;

import io.elice.pokeranger.global.enums.UserType;
import io.elice.pokeranger.global.exception.ExceptionCode;
import io.elice.pokeranger.global.exception.ServiceLogicException;
import io.elice.pokeranger.user.entity.*;
import io.elice.pokeranger.user.repository.UserRepository;
import io.elice.pokeranger.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setType(UserType.User);

        user.setDeletedAt(new Date(0));
        user.setPasswordHash(passwordEncoder.encode(registerDTO.getPassword()));

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
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }
    public Page<User> getAll(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    public User getUserPasswordHash(LoginDTO loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        if(  passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash()))
        {
            return user;
        }else{
            return null;
        }
    }

    public UserDTO userRoleChange(Long userId, UserTypeDTO userTypeDTO){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ServiceLogicException(ExceptionCode.USER_NOT_FOUND));
        user.setType(userTypeDTO.getRoles());
        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }
}