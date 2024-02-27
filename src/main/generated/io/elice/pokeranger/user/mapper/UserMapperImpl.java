package io.elice.pokeranger.user.mapper;

import io.elice.pokeranger.enums.UserType;
import io.elice.pokeranger.user.entity.User;
import io.elice.pokeranger.user.entity.UserDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-27T20:58:13+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        String email = null;
        String name = null;
        UserType type = null;

        email = userDTO.getEmail();
        name = userDTO.getName();
        type = userDTO.getType();

        String passwordHash = null;
        String phoneNumber = null;
        String address = null;

        User user = new User( type, email, passwordHash, name, phoneNumber, address );

        user.setId( userDTO.getId() );
        user.setPasswordHash( userDTO.getPasswordHash() );
        user.setPhoneNumber( userDTO.getPhoneNumber() );
        user.setAddress( userDTO.getAddress() );

        return user;
    }

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPasswordHash( user.getPasswordHash() );
        userDTO.setName( user.getName() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );
        userDTO.setAddress( user.getAddress() );
        userDTO.setType( user.getType() );

        return userDTO;
    }
}
