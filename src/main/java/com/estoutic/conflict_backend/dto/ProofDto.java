package com.estoutic.conflict_backend.dto;

import com.estoutic.conflict_backend.database.enitities.Proof;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ProofDto {

    private UUID id;

    private UUID userId;

    private String description;

    private UUID conflictId;

    public ProofDto(Proof proof) {
        this.id = proof.getId();
        this.userId = proof.getUser().getId();
        this.description = proof.getDescription();
        this.conflictId = proof.getConflict().getId();
    }
}
