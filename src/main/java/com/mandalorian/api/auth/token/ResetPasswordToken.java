package com.mandalorian.api.auth.token;

import com.mandalorian.api.auth.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.joda.time.LocalDateTime;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ResetPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)", length = 36)
    private String id;
    private String token;
    @ManyToOne
    @JoinColumn(columnDefinition = "char(36)")
    private User user;
    private LocalDateTime expiresAt;
}
