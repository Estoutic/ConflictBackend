package com.estoutic.conflict_backend.database.repostiories;

import com.estoutic.conflict_backend.database.enitities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Boolean existsByRoleName(String roleName);

    Role findByRoleName(String roleName);
}
