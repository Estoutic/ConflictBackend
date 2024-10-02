package com.estoutic.conflict_backend.services.conflict.impl;

import com.estoutic.conflict_backend.database.enitities.Conflict;
import com.estoutic.conflict_backend.database.repostiories.ConflictRepository;
import com.estoutic.conflict_backend.services.conflict.IConflictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConflictService implements IConflictService {

    private final ConflictRepository conflictRepository;

    @Override
    public Integer create() {
        Conflict conflict = new Conflict();
        return conflictRepository.save(conflict).getId();
    }
}
