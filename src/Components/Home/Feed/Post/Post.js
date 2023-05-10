import React from "react";
import "./Post.css";

import { Avatar } from "@mui/material";
import { 
    ChatBubbleOutline, 
    FavoriteBorderOutlined, 
    MoreHoriz, 
    PublishOutlined, 
    Repeat 
} from "@mui/icons-material";

const Post = () => {
    return(
        <div className="post">
            <Avatar src="https://shapka-youtube.ru/wp-content/uploads/2021/03/mrachnaya-avatarka-dlya-parney.jpg" className="post_avatar" />
            <div className="post_content">
                <div className="post_header">
                    <div className="post_titles">
                        <h3>Jack Richard</h3>
                        <h4>@jackrichard</h4>
                    </div>
                    <MoreHoriz className="post_options" />
                </div>
                <div className="post_desc">
                    Ajax is an abbreviation for “Asynchronous JavaScript and XML”. It is a technology for communicating with the server
                    without reloading the page. The technology is based on the use of a non-standard XMLHttpRequest object, which is necessary 
                    for interaction with scripts on the server side.
                </div>
                <div className="post_media">
                    <img src="https://img.freepik.com/premium-photo/male-programmer-is-writing-program-code-on-a-laptop-at-home_97716-1206.jpg" alt="post_picture" />
                </div>
            </div>
            <div className="post_footer">
                <ChatBubbleOutline fontSize="small"/>
                <Repeat fontSize="small"/>
                <FavoriteBorderOutlined fontSize="small"/>
                <PublishOutlined fontSize="small"/>
            </div>
        </div>
    )
}

export default Post;