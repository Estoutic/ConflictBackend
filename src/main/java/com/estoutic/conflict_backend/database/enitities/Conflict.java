package com.estoutic.conflict_backend.database.enitities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private Instant createdAt;

    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conflict")
    @JsonIgnore
    private Set<Proof> proofs;

    public Conflict() {
        this.status = true;
        this.proofs = new HashSet<>();
    }

    public void addProof(Proof proof) {
        if (!this.proofs.contains(proof)) {
            this.proofs.add(proof);
        }
    }
}
