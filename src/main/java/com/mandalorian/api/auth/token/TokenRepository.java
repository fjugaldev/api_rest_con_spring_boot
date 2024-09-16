package com.mandalorian.api.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

    @Query(value = """
            SELECT t FROM Token t INNER JOIN User u\s
            ON t.user.id = u.id\s
            WHERE u.id = :id AND (t.expired = FALSE OR t.revoked = FALSE)\s
            """)
    List<Token> findAllValidTokenByUser(@Param("id") String id);

    // When using Datastore as database
//    List<Token> findByUserIdAndExpiredAndRevoked(String id, Boolean expired, Boolean revoked);

    Optional<Token> findByToken(String token);

}
