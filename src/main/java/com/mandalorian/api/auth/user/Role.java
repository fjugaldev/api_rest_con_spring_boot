package com.mandalorian.api.auth.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)", length = 36)
    private String id;

    private String name;
    private String code;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_privileges",
        joinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id", columnDefinition = "char(36)"),
        inverseJoinColumns = @JoinColumn(
            name = "privilege_id", referencedColumnName = "id", columnDefinition = "char(36)"))
    private Collection<Privilege> privileges;
}

