import React from "react";
import "./Post.css";

import { Avatar } from "@mui/material";
import { 
    ChatBubbleOutline,
    MoreHoriz, 
    PublishOutlined, 
    Repeat 
} from "@mui/icons-material";

const Post = () => {

    let num_dow = 0;
    let num_shr = 0;
    let num_like = 0;
    let num_com = 0;
    /*
    const like = document.getElementById("like");
    like.addEventListener("click", () => {
        num_like++;
        return num_like;
    });
    */
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
                    <img width="10px" height="10px" src="https://img.freepik.com/premium-photo/male-programmer-is-writing-program-code-on-a-laptop-at-home_97716-1206.jpg" alt="post_picture" />
                </div>
            </div>
            <div className="post_footer">
                <button class="post__but-icon" type="button">
                    <PublishOutlined fontSize="small"/>
                    <p>{num_dow}</p>
                </button>
                <button class="post__but-icon" type="button">
                    <Repeat fontSize="small"/>
                    <p>{num_shr}</p>
                </button>
                <button id="like" class="post__but-icon" type="button">
                    <img  src="https://www.svgrepo.com/show/220662/like.svg" alt="like" />
                    <p>{num_like}</p>
                </button>
                <button id="comment" class="post__but-icon" type="button">
                    <ChatBubbleOutline fontSize="small"/>
                    <p>{num_com}</p> 
                </button>
            </div>
        </div>
    )
}

export default Post;