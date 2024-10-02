package com.estoutic.conflict_backend.services.conflict;

import com.estoutic.conflict_backend.dto.ProofDto;

import java.util.UUID;

public interface IProofService {

    UUID add(ProofDto proofDto);
}
