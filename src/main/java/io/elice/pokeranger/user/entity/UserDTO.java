package io.elice.pokeranger.user.entity;
import io.elice.pokeranger.global.enums.UserType;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String passwordHash;
    private String name;
    private String phoneNumber;
    private String address;
    private UserType type;
}
