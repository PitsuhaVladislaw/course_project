package com.itransition.backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private final Integer id;

    private final String authority;

}
