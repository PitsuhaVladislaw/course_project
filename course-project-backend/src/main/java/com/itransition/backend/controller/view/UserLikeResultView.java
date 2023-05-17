package com.itransition.backend.controller.view;

import org.springframework.beans.factory.annotation.Value;

public interface UserLikeResultView {

    @Value("#{target.is_like}")
    Boolean getIsLike();

    @Value("#{target.user_post_id}")
    Long getPostId();

}
