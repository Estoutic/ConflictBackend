package com.estoutic.conflict_backend.database.enitities;


import com.estoutic.conflict_backend.dto.ProofDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@NoArgsConstructor
@Data
@Table
@Entity
public class Proof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private Instant createAt;

    @UpdateTimestamp
    private Instant updateAt;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conflict_id")
    private Conflict conflict;

    public Proof(ProofDto proofDto) {
        this.description = proofDto.getDescription();
    }
}
