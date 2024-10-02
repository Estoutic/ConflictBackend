package com.estoutic.conflict_backend.database.enitities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private Instant createdAt;

    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conflict")
    private Set<Proof> proofs;

    public Conflict() {
        this.status = true;
        this.proofs = new HashSet<>();
    }

    public void addProof(Proof proof){
        this.proofs.add(proof);
    }
}
