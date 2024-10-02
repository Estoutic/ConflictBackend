package com.estoutic.conflict_backend.services.conflict.impl;

import com.estoutic.conflict_backend.database.enitities.Conflict;
import com.estoutic.conflict_backend.database.enitities.Proof;
import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.database.repostiories.ConflictRepository;
import com.estoutic.conflict_backend.database.repostiories.ProofRepository;
import com.estoutic.conflict_backend.dto.ConflictDto;
import com.estoutic.conflict_backend.exceptions.conflict.ConflictDoesNotExistException;
import com.estoutic.conflict_backend.services.auth.impl.AuthService;
import com.estoutic.conflict_backend.services.conflict.IConflictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConflictService implements IConflictService {

    private final ConflictRepository conflictRepository;
    private final ProofRepository proofRepository;
    private final AuthService authService;

    @Override
    public UUID create() {
        Conflict conflict = new Conflict();
        return conflictRepository.save(conflict).getId();
    }

    @Override
    public List<ConflictDto> getAll() {

        User user = authService.getUserBySession();

        List<Proof> proofs = proofRepository.findAllByUser(user);
        List<Conflict> conflicts = proofs.stream().map(Proof::getConflict).toList();
//        List<Conflict> conflicts = user.getProofs().stream().map(Proof::getConflict).toList();

        return conflicts.stream().map(ConflictDto::new).toList();

    }
}
