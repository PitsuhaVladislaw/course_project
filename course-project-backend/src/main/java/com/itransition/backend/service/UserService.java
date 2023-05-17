package com.itransition.backend.service;

import com.itransition.backend.controller.request.UserRequest;
import com.itransition.backend.controller.view.LikeCountView;
import com.itransition.backend.controller.view.UserLikeResultView;
import com.itransition.backend.dto.UserCommentDto;
import com.itransition.backend.dto.UserDto;
import com.itransition.backend.dto.UserPostDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(int page, int size);

    UserDto getUserByName(String username);

    UserDto getUserById(Long userId);

    UserDto saveUser(UserRequest userRequest);

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(Long userId, UserDto userDto);

    void updatePassword(Long userId, String password);

    void deleteUser(Long userId);

    List<UserPostDto> getUserPostsByUserId(Long userId);

    UserPostDto saveUserPost(Long userId, UserPostDto userPostDto);

    UserPostDto updateUserPost(Long userPostId, String content);

    void deleteUserPost(Long userPostId);

    List<UserCommentDto> getUserCommentsByUserId(Long userId);

    List<UserCommentDto> getUserCommentsByUserPostId(Long userPostId);

    UserCommentDto saveUserComment(Long userId, Long userPostId, UserCommentDto userCommentDto);

    UserCommentDto updateUserComment(Long userCommentId, String comment);

    void deleteUserComment(Long userCommentId);

    LikeCountView getUserUserLikeCountByUserId(Long userId);

    LikeCountView getUserUserLikeCountByUserPostId(Long userPostId);

    List<UserLikeResultView> getAllUserLikeResultByUserId(Long userId);

    List<UserLikeResultView> getAllUserLikeResultUserPostId(Long userPostId);

    void updateUserLike(Long userId, Long userPostId, Boolean isLike);

}
