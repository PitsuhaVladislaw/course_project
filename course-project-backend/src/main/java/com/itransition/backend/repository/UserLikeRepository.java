package com.itransition.backend.repository;

import com.itransition.backend.controller.view.LikeCountView;
import com.itransition.backend.controller.view.UserLikeResultView;
import com.itransition.backend.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    @Query(value = "select sum(case when u.is_like then 1 else 0 end) as like_count, sum(case when u.is_like = false then 1 else 0 end) as dislike_count from user_likes u where user_id = :userId", nativeQuery = true)
    LikeCountView getUserUserLikeCountByUserId(@Param("userId") Long userId);

    @Query(value = "select sum(case when u.is_like then 1 else 0 end) as like_count, sum(case when u.is_like = false then 1 else 0 end) as dislike_count from user_likes u where user_post_id = :userPostId", nativeQuery = true)
    LikeCountView getUserUserLikeCountByUserPostId(@Param("userPostId") Long userPostId);

    @Query(value = "select u.is_like from user_likes u where u.user_id = :userId", nativeQuery = true)
    List<UserLikeResultView> getAllUserLikeResultByUserId(@Param("userId") Long userId);

    @Query(value = "select u.is_like from user_likes u where u.user_post_id = :userPostId", nativeQuery = true)
    List<UserLikeResultView> getAllUserLikeResultUserPostId(@Param("userPostId") Long userPostId);

    @Query("select case when count(u) > 0 then true else false end from UserLike u where exists (select ul from UserLike ul where ul.user.id = :userId and ul.userPost.id = :userPostId)")
    boolean existsByUserIdAndUserPostId(@Param("userId") Long userId, @Param("userPostId") Long userPostId);

    @Modifying(clearAutomatically = true)
    @Query("delete from UserLike u where u.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query("delete from UserLike u where u.userPost.id = :userPostId")
    void deleteByUserPostId(@Param("userPostId") Long userPostId);

    @Modifying(clearAutomatically = true)
    @Query("delete from UserLike u where u.user.id = :userId and u.userPost.id = :userPostId")
    void deleteByUserIdAndUserPostId(@Param("userId") Long userId, @Param("userPostId") Long userPostId);

    @Modifying(clearAutomatically = true)
    @Query("update UserLike u set u.isLike = :isLike where u.user.id = :userId and u.userPost.id = :userPostId")
    void updateByUserIdAndUserPostId(@Param("userId") Long userId, @Param("userPostId") Long userPostId, @Param("userId") Boolean isLike);

}
