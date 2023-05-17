package com.itransition.backend.entity;

import com.itransition.backend.entity.domain.AbstractTimestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_posts")
public class UserPost extends AbstractTimestamp {

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "userPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserLike> userLikes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;
}
