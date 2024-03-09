package io.elice.pokeranger.user.entity;

import io.elice.pokeranger.global.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity

@Data
@NoArgsConstructor
@Table(name = "User")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long  id ;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 100)
    private String name = "1";

    @Column(length = 20)
    private String phoneNumber= "1";

    @Column( length = 255)
    private String address= "1";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    @CreatedDate
    private Date createdAt = new Date(0);

    @LastModifiedDate
    private Date updatedAt= new Date(0);

    private Date deletedAt= new Date(0);
    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {

    }
    public User(UserType type, String email, String PasswordHash, String name, String PhoneNumber, String Address) {
        this.type = type;
        this.email = email;
        this.passwordHash = PasswordHash;
        this.name = name;
        this.phoneNumber = PhoneNumber;
        this.address = Address;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // UserType에 따라 권한을 설정합니다.
        String rolePrefix = "ROLE_"; // Spring Security에서는 권한을 'ROLE_'로 시작하는 것이 규칙입니다.
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(rolePrefix + this.type.name()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static ElasticsearchConnectionDetails.Node builder() {
        return null;
    }


}
