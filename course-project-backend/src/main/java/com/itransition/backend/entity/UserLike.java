package com.itransition.backend.entity;

import com.itransition.backend.entity.domain.AbstractTimestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_likes")
public class UserLike extends AbstractTimestamp {

    @Column(name = "is_like", nullable = false)
    private Boolean isLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private UserPost userPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

}
