package com.estoutic.conflict_backend.database.enitities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private Instant createdAt;

    private Boolean status;

    @ManyToMany(mappedBy = "conflicts", cascade = CascadeType.ALL)
    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conflict", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Proof> proofs;

    public Conflict(User user) {
        this.status = true;
        this.proofs = new HashSet<>();
        this.users = new HashSet<>();
        this.users.add(user);
        user.addConflict(this);
    }

    public Conflict() {

    }

    public void addProof(Proof proof) {
        this.proofs.add(proof);
    }
}
