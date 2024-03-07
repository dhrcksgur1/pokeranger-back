package io.elice.pokeranger.user.entity;

import io.elice.pokeranger.global.enums.UserType;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String passwordHash;
    private String name;
    private String phoneNumber;
    private String address;
    private UserType type;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
