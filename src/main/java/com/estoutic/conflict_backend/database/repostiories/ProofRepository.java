package com.estoutic.conflict_backend.database.repostiories;

import com.estoutic.conflict_backend.database.enitities.Proof;
import com.estoutic.conflict_backend.database.enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProofRepository extends JpaRepository<Proof, UUID> {

    List<Proof> findAllById(UUID id);

    List<Proof> findAllByUser(User user);
}
