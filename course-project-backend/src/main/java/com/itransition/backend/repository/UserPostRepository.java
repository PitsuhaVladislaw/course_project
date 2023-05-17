package com.itransition.backend.repository;

import com.itransition.backend.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {

    @Query("select u from UserPost u where u.user.id = :userId")
    List<UserPost> findAllByUserId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query("delete from UserPost u where u.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);

}
