package com.estoutic.conflict_backend.database.enitities;


import com.estoutic.conflict_backend.dto.ProofDto;
import com.estoutic.conflict_backend.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Table
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Proof {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private Instant createAt;

    @UpdateTimestamp
    private Instant updateAt;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "conflict_id")
    @JsonIgnore
    private Conflict conflict;

    public Proof(ProofDto proofDto, User user, Conflict conflict) {
        this.description = proofDto.getDescription();
        this.user = user;
        this.conflict = conflict;
    }

    public void setUser(User user) {
        this.user = user;
        user.addProof(this);

    }

    public void setConflict(Conflict conflict) {
        this.conflict = conflict;
        conflict.addProof(this);
    }
}
