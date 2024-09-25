package com.estoutic.conflict_backend.database.enitities;

import com.estoutic.conflict_backend.annotations.encode_password.EncodePassword;
import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.utils.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;


    public String username;

    public String password;

    public String phone;

    @Column(name = "is_verified", nullable = true)
    private Boolean is_verified = false;

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.is_verified;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
//        singletonList(() -> this.role.name());
    }
    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = PasswordEncoder.getInstance().encode(userDto.getPassword());
        this.phone = userDto.getPhone();
    }
}
