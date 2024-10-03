package com.estoutic.conflict_backend.services.conflict.impl;

import com.estoutic.conflict_backend.database.enitities.Conflict;
import com.estoutic.conflict_backend.database.enitities.Proof;
import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.database.repostiories.ConflictRepository;
import com.estoutic.conflict_backend.database.repostiories.ProofRepository;
import com.estoutic.conflict_backend.database.repostiories.UserRepository;
import com.estoutic.conflict_backend.dto.ConflictDto;
import com.estoutic.conflict_backend.dto.ProofDto;
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
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    public UUID create() {
        User user = authService.getUserBySession();
        if (user != null){
            Conflict conflict = new Conflict(user);
            return conflictRepository.save(conflict).getId();
        }
        return null;
    }

    @Override
    public List<ConflictDto> getAll() {

//        User user = authService.getUserBySession();
//
//        List<Proof> proofs = proofRepository.findAllByUser(user);
//        List<Conflict> conflicts = proofs.stream().map(Proof::getConflict).toList();
////        List<Conflict> conflicts = user.getProofs().stream().map(Proof::getConflict).toList();
//        return conflicts.stream().map(
//                conflict -> {
//                    List<Proof> matchingProof = proofs.stream().filter(
//                            proof -> proof.getConflict().equals(conflict)
//                    ).toList();
//                    List<User> users = userRepository.f
//                    return new ConflictDto(conflict, matchingProof.stream().map(ProofDto::new).toList());
//                }
//        ).toList();

        return null;

    }
}
