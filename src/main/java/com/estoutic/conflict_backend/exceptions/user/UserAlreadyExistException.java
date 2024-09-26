package com.estoutic.conflict_backend.exceptions.user;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String userId) {
        super(String.format("User already exist %s", userId));
    }
}
