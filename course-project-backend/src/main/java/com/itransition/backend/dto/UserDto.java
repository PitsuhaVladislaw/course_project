package com.itransition.backend.dto;

import com.itransition.backend.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto extends AbstractLongIdentifiableDto {

    @NotNull
    @NotBlank
    private String name;

    @Email
    private String email;

    private Role role;

}
