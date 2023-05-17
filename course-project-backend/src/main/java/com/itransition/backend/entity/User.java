package com.itransition.backend.entity;

import com.itransition.backend.entity.converter.RoleConverter;
import com.itransition.backend.entity.domain.AbstractTimestamp;
import com.itransition.backend.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends AbstractTimestamp {

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Convert(converter = RoleConverter.class)
    @Column(name = "role")
    private Role role;

}
