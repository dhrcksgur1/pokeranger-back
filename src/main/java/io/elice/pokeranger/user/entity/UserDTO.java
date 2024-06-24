package io.elice.pokeranger.user.entity;

import io.elice.pokeranger.global.enums.UserType;
import lombok.*;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String passwordHash;
    private String name;
    private String phoneNumber;
    private AddressDTO address;
    private UserType type;
    private Date createdAt;
    private Date updatedAt;
}
