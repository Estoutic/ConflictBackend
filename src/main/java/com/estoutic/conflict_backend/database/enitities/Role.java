package com.estoutic.conflict_backend.database.enitities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<User> users;

}
