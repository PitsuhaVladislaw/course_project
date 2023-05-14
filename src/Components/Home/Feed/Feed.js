import React from "react";
import "./Feed.css";

import ContentBox from "./ContentBox/ContentBox";
import Post from "./Post/Post";

const Feed = () => {
    return(
        <div className="feed">
            <header className="feed_header">
                <h2>Home</h2>
            </header>
            <ContentBox />
            <Post />
            <Post />
        </div>
    )
}

export default Feed;