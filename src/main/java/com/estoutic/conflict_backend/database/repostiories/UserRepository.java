package com.estoutic.conflict_backend.database.repostiories;

import com.estoutic.conflict_backend.database.enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Boolean existsByUsername(String username);

    Boolean existsByPhone(String phone);

    Optional<User> findByUsername(String username);

}
