package com.estoutic.conflict_backend.database.enitities;

import com.estoutic.conflict_backend.annotations.encode_password.EncodePassword;
import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.utils.PasswordEncoder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;


    public String username;

    public String password;

    public String phone;

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = PasswordEncoder.getInstance().encode(userDto.getPassword());
        this.phone = userDto.getPhone();
    }
}
