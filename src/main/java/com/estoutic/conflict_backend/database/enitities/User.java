package com.estoutic.conflict_backend.database.enitities;

import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.utils.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String username;

    private String password;

    private String phone;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Proof> proofs;

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
        this.roles = new HashSet<>();
        this.proofs = new HashSet<>();
        this.username = userDto.getUsername();
        this.password = PasswordEncoder.getInstance().encode(userDto.getPassword());
        this.phone = userDto.getPhone();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void addProof(Proof proof) {
        if (!this.proofs.contains(proof)) {
            this.proofs.add(proof);
        }
    }
}
