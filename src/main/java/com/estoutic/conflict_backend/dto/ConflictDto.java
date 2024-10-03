package com.estoutic.conflict_backend.dto;

import com.estoutic.conflict_backend.database.enitities.Conflict;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConflictDto {

    private UUID id;

    private Boolean status;

    private List<ProofDto> proofs;

    public ConflictDto(Conflict conflict, List<ProofDto> proofs) {
        this.id = conflict.getId();
        this.status = conflict.getStatus();
        this.proofs = proofs;
    }
}
