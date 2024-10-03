package com.estoutic.conflict_backend.services.conflict.impl;

import com.estoutic.conflict_backend.database.enitities.Conflict;
import com.estoutic.conflict_backend.database.enitities.Proof;
import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.database.repostiories.ConflictRepository;
import com.estoutic.conflict_backend.database.repostiories.ProofRepository;
import com.estoutic.conflict_backend.database.repostiories.UserRepository;
import com.estoutic.conflict_backend.dto.ProofDto;
import com.estoutic.conflict_backend.exceptions.conflict.ConflictDoesNotExistException;
import com.estoutic.conflict_backend.services.auth.impl.AuthService;
import com.estoutic.conflict_backend.services.conflict.IProofService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProofService implements IProofService {

    private final ProofRepository proofRepository;
    private final AuthService authService;
    private final ConflictRepository conflictRepository;
    private final UserRepository userRepository;

    @Override
    public UUID add(ProofDto proofDto) {
        User user = authService.getUserBySession();
        Conflict conflict = conflictRepository.findById(proofDto.getConflictId())
                .orElseThrow(ConflictDoesNotExistException::new);
        Proof proof = new Proof(proofDto);

        proof.setConflict(conflict);
        proof.setUser(user);

        proofRepository.save(proof);

//        user.addProof(proof);
//        conflict.addProof(proof);

//        userRepository.save(user);
//        conflictRepository.save(conflict);

        return proof.getId();
    }
}
