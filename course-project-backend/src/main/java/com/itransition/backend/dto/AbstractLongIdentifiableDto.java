package com.itransition.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractLongIdentifiableDto implements IdentifiableDto<Long> {

    private Long id;

}
