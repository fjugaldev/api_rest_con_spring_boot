package com.mandalorian.api.auth.user;

import com.mandalorian.api.auth.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.LocalDateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)", length = 36)
    private String id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Transient
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Transient
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id", columnDefinition = "char(36)"),
        inverseJoinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id", columnDefinition = "char(36)"))
    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getCode());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            if (!privileges.contains(item.getName())) {
                privileges.add(item.getName());
            }
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", privilege).toUpperCase()));
        }
        return authorities;
    }

    public List<String> getRolesAsListOfStrings() {
        return roles.stream().map(role -> role.getCode().toUpperCase()).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getEnabled();
    }
}
