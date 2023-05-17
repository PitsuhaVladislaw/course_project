package com.itransition.backend.controller.request;

import com.itransition.backend.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequest extends UserDto {

    @NotNull
    @NotBlank
    private String password;

}
