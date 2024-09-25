package com.estoutic.conflict_backend.dto;

//import com.estoutic.conflict_backend.annotations.password_mathces.PasswordMatches;
import com.estoutic.conflict_backend.database.enitities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
//@PasswordMatches
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude
public class UserDto {

    private Integer id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotEmpty
    @NotNull
    private String phone;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
    }
}
