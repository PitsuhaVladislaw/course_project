package com.itransition.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCommentDto extends AbstractLongIdentifiableDto {

    @NotBlank
    private String comment;

    @NotNull
    private Long userPostId;

    @NotNull
    private Long userId;

}
