package com.itransition.backend.service.impl;

import com.itransition.backend.controller.request.UserRequest;
import com.itransition.backend.controller.view.LikeCountView;
import com.itransition.backend.controller.view.UserLikeResultView;
import com.itransition.backend.dto.UserCommentDto;
import com.itransition.backend.dto.UserDto;
import com.itransition.backend.dto.UserPostDto;
import com.itransition.backend.entity.User;
import com.itransition.backend.entity.UserComment;
import com.itransition.backend.entity.UserLike;
import com.itransition.backend.entity.UserPost;
import com.itransition.backend.mapper.UserMapper;
import com.itransition.backend.repository.UserCommentRepository;
import com.itransition.backend.repository.UserLikeRepository;
import com.itransition.backend.repository.UserPostRepository;
import com.itransition.backend.repository.UserRepository;
import com.itransition.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.itransition.backend.util.Constants.INVALID_USER_ID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserPostRepository userPostRepository;
    private final UserCommentRepository userCommentRepository;
    private final UserLikeRepository userLikeRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers(int page, int size) {
        log.info("Get all users by page = {}, size = {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto getUserByName(String username) {
        log.info("Get user by username = {}", username);
        return userMapper.toDto(userRepository.findByName(username));
    }

    @Override
    public UserDto getUserById(Long userId) {
        log.info("Get user by id {}", userId);
        var user = userRepository.findById(userId).orElseThrow();
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserDto saveUser(UserRequest userRequest) {
        log.info("Save user");
        var user = userMapper.toEntity(userRequest);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserDto saveUser(UserDto userDto) {
        log.info("Save user");
        var user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        log.info("Update user - userId {}", userId);
        if (!Objects.equals(userId, userDto.getId())) {
            throw new IllegalArgumentException(INVALID_USER_ID + userId);
        }
        var user = userRepository.getById(userId);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public void updatePassword(Long userId, String password) {
        log.info("Update password for user id {}", userId);
        var user = userRepository.getById(userId);
        user.setPassword(passwordEncoder.encode(password));
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        log.info("Delete user id {}", userId);
        userLikeRepository.deleteByUserId(userId);
        userCommentRepository.deleteByUserId(userId);
        userPostRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserPostDto> getUserPostsByUserId(Long userId) {
        log.info("Get by user id {}", userId);
        return userMapper.toUserPostDtos(userPostRepository.findAllByUserId(userId));
    }

    @Transactional
    @Override
    public UserPostDto saveUserPost(Long userId, UserPostDto userPostDto) {
        log.info("Save user post");
        UserPost userPost = userMapper.toEntity(userPostDto);
        userPost.setUser(userRepository.findById(userId).orElseThrow());
        return userMapper.toDto(userPostRepository.save(userPost));
    }

    @Transactional
    @Override
    public UserPostDto updateUserPost(Long userPostId, String content) {
        log.info("Update user post id {}", userPostId);
        UserPost userPost = userPostRepository.findById(userPostId).orElseThrow();
        userPost.setContent(content);
        return userMapper.toDto(userPost);
    }

    @Transactional
    @Override
    public void deleteUserPost(Long userPostId) {
        log.info("Delete user post by id {}", userPostId);
        userLikeRepository.deleteByUserPostId(userPostId);
        userCommentRepository.deleteByUserPostId(userPostId);
        userPostRepository.deleteById(userPostId);
    }

    @Override
    public List<UserCommentDto> getUserCommentsByUserId(Long userId) {
        log.info("Get by user id {}", userId);
        return userMapper.toUserCommentDtos(userCommentRepository.findAllByUserId(userId));
    }

    @Override
    public List<UserCommentDto> getUserCommentsByUserPostId(Long userPostId) {
        log.info("Get user comments by userPost id {}", userPostId);
        return userMapper.toUserCommentDtos(userCommentRepository.findAllByUserPostId(userPostId));
    }

    @Transactional
    @Override
    public UserCommentDto saveUserComment(Long userId, Long userPostId, UserCommentDto userCommentDto) {
        log.info("Save user comment");
        UserComment userComment = userMapper.toEntity(userCommentDto);
        userComment.setUser(userRepository.findById(userId).orElseThrow());
        userComment.setUserPost(userPostRepository.findById(userPostId).orElseThrow());
        return userMapper.toDto(userCommentRepository.save(userComment));
    }

    @Transactional
    @Override
    public UserCommentDto updateUserComment(Long userCommentId, String comment) {
        log.info("Update user comment");
        UserComment userComment = userCommentRepository.findById(userCommentId).orElseThrow();
        userComment.setComment(comment);
        return userMapper.toDto(userComment);
    }

    @Transactional
    @Override
    public void deleteUserComment(Long userCommentId) {
        log.info("Delete user comment by id {}", userCommentId);
        userCommentRepository.deleteById(userCommentId);
    }

    @Override
    public LikeCountView getUserUserLikeCountByUserId(Long userId) {
        log.info("Get user likes count by userId {}", userId);
        return userLikeRepository.getUserUserLikeCountByUserId(userId);
    }

    @Override
    public LikeCountView getUserUserLikeCountByUserPostId(Long userPostId) {
        log.info("Get user likes count by userPostId {}", userPostId);
        return userLikeRepository.getUserUserLikeCountByUserPostId(userPostId);
    }

    @Override
    public List<UserLikeResultView> getAllUserLikeResultByUserId(Long userId) {
        log.info("Get user likes by userId {}", userId);
        return userLikeRepository.getAllUserLikeResultByUserId(userId);
    }

    @Override
    public List<UserLikeResultView> getAllUserLikeResultUserPostId(Long userPostId) {
        log.info("Get user likes by userPostId {}", userPostId);
        return userLikeRepository.getAllUserLikeResultUserPostId(userPostId);
    }

    @Transactional
    @Override
    public void updateUserLike(Long userId, Long userPostId, Boolean isLike) {
        log.info("Update user like by userId {} and userPostId {}", userId, userPostId);
        if (isLike == null) {
            userLikeRepository.deleteByUserIdAndUserPostId(userId, userPostId);
        } else {
            boolean result = userLikeRepository.existsByUserIdAndUserPostId(userId, userPostId);
            if (result) {
                userLikeRepository.updateByUserIdAndUserPostId(userId, userPostId, isLike);
            } else {
                UserLike userLike = new UserLike();
                userLike.setUser(userRepository.findById(userId).orElseThrow());
                userLike.setUserPost(userPostRepository.findById(userPostId).orElseThrow());
                userLikeRepository.save(userLike);
            }
        }
    }
}
