package com.estoutic.conflict_backend.database.enitities;

import com.estoutic.conflict_backend.annotations.encode_password.EncodePassword;
import com.estoutic.conflict_backend.dto.UserDto;
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


    public String userName;

    public String password;

    public String phone;

    @EncodePassword
    public User(UserDto userDto) {
        this.userName = userDto.getUserName();
        this.password = userDto.getPassword();
        this.phone = userDto.getPhone();
    }
}
