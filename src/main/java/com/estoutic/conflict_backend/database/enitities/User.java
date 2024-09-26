package com.estoutic.conflict_backend.database.enitities;

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
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String username;

    private String password;

    private String phone;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> roleList;

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
    }

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = PasswordEncoder.getInstance().encode(userDto.getPassword());
        this.phone = userDto.getPhone();
    }

    public void addRole(Role role) {
        if (!this.roleList.contains(role)) {
            this.roleList.add(role);
        } else {
            throw new RuntimeException("User already has role");
        }
    }
}
