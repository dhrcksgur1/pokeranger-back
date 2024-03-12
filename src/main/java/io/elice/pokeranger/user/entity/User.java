package io.elice.pokeranger.user.entity;

import io.elice.pokeranger.global.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long  id ;

    @Column(nullable = false, length = 100)
    private  String email ="";

    @Column(nullable = false, length = 255)
    private  String passwordHash="";

    @Column(nullable = false, length = 100)
    private  String name="";

    @Column(length = 20)
    private  String phoneNumber="";

    @Embedded
    private  AddressDTO address = new AddressDTO();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private  UserType type;

    @CreatedDate
    @Column
    private  Date createdAt ;

    @Column
    @LastModifiedDate
    private  Date updatedAt = new Date(0);

    @Column
    private Date deletedAt= new Date(0);;
    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {

    }
public User(UserType type, String email, String PasswordHash, String name, String PhoneNumber, AddressDTO Address) {
        this.type = type;
        this.email = email;
        this.passwordHash = PasswordHash;
        this.name = name;
        this.phoneNumber = PhoneNumber;
        this.address = Address;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String rolePrefix = "ROLE_";
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


}
