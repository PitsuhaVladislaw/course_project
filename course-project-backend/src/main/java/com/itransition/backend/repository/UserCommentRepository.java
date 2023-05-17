package com.itransition.backend.repository;

import com.itransition.backend.entity.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCommentRepository extends JpaRepository<UserComment, Long> {

    @Query("select u from UserComment u where u.user.id = :userId")
    List<UserComment> findAllByUserId(@Param("userId") Long userId);

    @Query("select u from UserComment u where u.userPost.id = :userPostId order by u.createDate")
    List<UserComment> findAllByUserPostId(@Param("userPostId") Long userPostId);

    @Modifying(clearAutomatically = true)
    @Query("delete from UserComment u where u.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query("delete from UserComment u where u.userPost.id = :userPostId")
    void deleteByUserPostId(@Param("userPostId") Long userPostId);

}
