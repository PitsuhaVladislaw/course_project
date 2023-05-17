package com.itransition.backend.entity;

import com.itransition.backend.entity.domain.AbstractTimestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends AbstractTimestamp {

    @Column(name = "token", unique = true, nullable = false, length = 600)
    private String token;

    @Column(name = "expire_date", nullable = false)
    private LocalDateTime expireDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

}
