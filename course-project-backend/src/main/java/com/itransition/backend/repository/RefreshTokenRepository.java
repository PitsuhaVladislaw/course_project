package com.itransition.backend.repository;

import com.itransition.backend.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query("select r from RefreshToken r where r.token = :token")
    Optional<RefreshToken> findByToken(@Param("token") String token);

    @Query("select r from RefreshToken r where r.user.id = :userId")
    Optional<RefreshToken> findByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("delete from RefreshToken r where r.expireDate < :expireDate")
    void deleteByExpireDate(@Param("expireDate") LocalDateTime expireDate);

}
