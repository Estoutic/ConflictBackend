package com.estoutic.conflict_backend.exceptions.conflict;

public class ConflictDoesNotExistException extends RuntimeException{
    public ConflictDoesNotExistException() {
        super("Conflict does not exist");
    }
}
