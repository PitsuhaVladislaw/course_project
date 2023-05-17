package com.itransition.backend.mapper;

import com.itransition.backend.controller.request.UserRequest;
import com.itransition.backend.dto.UserCommentDto;
import com.itransition.backend.dto.UserDto;
import com.itransition.backend.dto.UserPostDto;
import com.itransition.backend.entity.User;
import com.itransition.backend.entity.UserComment;
import com.itransition.backend.entity.UserLike;
import com.itransition.backend.entity.UserPost;
import com.itransition.backend.util.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = Constants.COMPONENT_MODEL)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest userRequest);

    User toEntity(UserDto userDto);

    @Mapping(target = "userLikes", ignore = true)
    UserPost toEntity(UserPostDto userPostDto);

    @Mapping(target = "userLikes", qualifiedByName = "toUserLikes")
    @Mapping(target = "userDto", source = "user")
    UserPostDto toDto(UserPost userPost);

    List<UserPostDto> toUserPostDtos(List<UserPost> userPosts);

    @Mapping(target = "userPostId", source = "userPost.id")
    @Mapping(target = "userId", source = "user.id")
    UserCommentDto toDto(UserComment userComment);

    List<UserCommentDto> toUserCommentDtos(List<UserComment> userComments);

    @Mapping(target = "id", ignore = true)
    UserComment toEntity(UserCommentDto userCommentDto);

    @Named("toUserLikes")
    default List<Boolean> toUserLikes(List<UserLike> userLikes) {
        return userLikes.stream().map(UserLike::getIsLike).toList();
    }

}
