package com.mandalorian.api.auth.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)", length = 36)
    private String id;

    private String name;

    private String code;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
