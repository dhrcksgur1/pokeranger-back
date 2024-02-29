package io.elice.pokeranger.user.entity;

import io.elice.pokeranger.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@Table(name = "User")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long  id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private Date deletedAt;

    public User(UserType type, String email, String PasswordHash, String name, String PhoneNumber, String Address) {
        this.type = type;
        this.email = email;
        this.passwordHash = PasswordHash;
        this.name = name;
        this.phoneNumber = PhoneNumber;
        this.address = Address;
    }
}
