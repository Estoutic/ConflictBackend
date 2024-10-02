package com.estoutic.conflict_backend.database.repostiories;

import com.estoutic.conflict_backend.database.enitities.Conflict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, UUID> {
}
