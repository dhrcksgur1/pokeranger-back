package io.elice.pokeranger.user.service;

import io.elice.pokeranger.global.enums.UserType;
import io.elice.pokeranger.global.exception.ExceptionCode;
import io.elice.pokeranger.global.exception.ServiceLogicException;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.user.entity.*;
import io.elice.pokeranger.user.mapper.UserMapper;
import io.elice.pokeranger.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        User originalUser = optionalUser.orElseThrow();
        return optionalUser.map(user -> {
            // Update user fields with values from userDTO
            String name = userDTO.getName() == "" ?  originalUser.getName() :userDTO.getName();
            user.setName(name);
            String email = userDTO.getEmail() == "" ?  originalUser.getEmail() :userDTO.getEmail();
            user.setEmail(email);

            AddressDTO address = userDTO.getAddress() == null ?  originalUser.getAddress() : userDTO.getAddress();
            user.setAddress(address);

            UserType userType = userDTO.getType() == null ?  originalUser.getType():userDTO.getType() ;
            user.setType(userType);

            String phoneNumber = userDTO.getPhoneNumber() == null ?  originalUser.getPhoneNumber(): userDTO.getPhoneNumber() ;
            user.setPhoneNumber(phoneNumber);

            String password = "";
            if (userDTO.getPasswordHash() != null) {
                user.setPasswordHash(passwordEncoder.encode(userDTO.getPasswordHash()));
            }


            userRepository.save(user);
            return userMapper.userToUserDTO(user);
        }).orElse(null);
    }

    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            /// 프로덕트와 오더 에 부모를 null 처리
            Optional<User> user = userRepository.findById(userId);
            if(user.isPresent())
            {
                user.get().getUserProductList().forEach(product -> {
                    product.setUser(null);
                });

                user.get().getUserOrdersList().stream().forEach(orders -> {
                    orders.setUser(null);
                });

                userRepository.deleteById(userId);
            }


        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }

public Page<UserDTO> getAll(Pageable pageable) {
    Page<UserDTO> users;
    Page<User> userPage = userRepository.findAll(pageable); // Fetching users from repository

    // Mapping User to UserDTO and returning a Page<UserDTO>
    users = userPage.map(user -> userMapper.userToUserDTO(user));

    return users;
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

    public UserDetails loadUserByUsername(String email, String password) {
        return userRepository.findByEmail(email).orElseThrow();

    }

    public Long checkPasswordHash(UserDTO checkUserDTO) {

        UserDTO getUserDTO = getUserById(checkUserDTO.getId());

        if(!passwordEncoder.matches(checkUserDTO.getPasswordHash(), getUserDTO.getPasswordHash()))
        {
            throw new RuntimeException("password is not match");

        }
        return getUserDTO.getId();
    }
}