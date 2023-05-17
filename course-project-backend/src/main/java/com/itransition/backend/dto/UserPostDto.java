package com.itransition.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserPostDto extends AbstractLongIdentifiableDto {

    private String content;

    private List<Boolean> userLikes = new ArrayList<>();

    private UserDto userDto;

}
