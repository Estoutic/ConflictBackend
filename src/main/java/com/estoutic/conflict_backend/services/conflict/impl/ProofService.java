package com.estoutic.conflict_backend.services.conflict.impl;

import com.estoutic.conflict_backend.database.enitities.Proof;
import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.database.repostiories.ProofRepository;
import com.estoutic.conflict_backend.database.repostiories.UserRepository;
import com.estoutic.conflict_backend.dto.ProofDto;
import com.estoutic.conflict_backend.services.auth.impl.AuthService;
import com.estoutic.conflict_backend.services.conflict.IProofService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProofService implements IProofService {

    private final ProofRepository proofRepository;
    private final AuthService authService;

    @Override
    public Integer add(ProofDto proofDto) {

        Proof proof = new Proof(proofDto);
        User user = authService.getUserBySession();

        proof.setUser(user);
        proofRepository.save(proof);

        return proof.getId();
    }
}
